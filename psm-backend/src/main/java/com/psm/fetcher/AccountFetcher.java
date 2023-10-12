package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.psm.custom.GraphQLException;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import com.psm.utils.Result;
import com.psm.vo.AccountVo;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;

@DgsComponent
public class AccountFetcher {
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;//用于加密密码

    public AccountFetcher(AccountMapper accountMapper, PasswordEncoder passwordEncoder){//springboot自动注入依赖
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @DgsMutation
    public AccountEntity register(@InputArgument String phoneNumber, @InputArgument String password){
        String salt = String.valueOf(Math.abs(new Date().toString().hashCode()));
        password = passwordEncoder.encode(password+salt);
        String token = passwordEncoder.encode(phoneNumber+password+new Date().toString());
        String userName = "新用户"+String.valueOf(Math.abs((salt+salt+token).hashCode()));
        String profile = "/images/Profile/defaultProfile.jpg";
        int status = 1;
        try{
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setPhoneNumber(phoneNumber);
            accountEntity.setPassword(password);
            accountEntity.setUserName(userName);
            accountEntity.setProfile(profile);
            accountEntity.setSalt(salt);
            accountEntity.setToken(token);
            accountEntity.setStatus(String.valueOf(status));
            accountMapper.addNewAccount(accountEntity);
            return accountEntity;
        }catch (Exception e){
            throw new GraphQLException("409", "手机号已被注册");
        }
    }

    @DgsQuery
    public AccountEntity login(@InputArgument String phoneNumber, @InputArgument String password){
        AccountEntity accountEntity;
        try {
            accountEntity = accountMapper.findByPhone(phoneNumber).get(0);
        } catch (Exception e) {
            throw new GraphQLException("404", "账号不存在，登录失败");
        }

        if(accountEntity.getPassword().equals(String.valueOf(Math.abs((password+accountEntity.getSalt()).hashCode())))){
            switch (Integer.parseInt(accountEntity.getStatus())){
                case 2:
                    throw new GraphQLException("403", "账号已被封禁");
                case 3:
                    throw new GraphQLException("410", "账号已注销");
                default:break;
            };

            return accountEntity;
        }else{
            throw new GraphQLException("401", "密码错误，登录失败");
        }
    }

    @DgsQuery
    public AccountEntity fasterLogin(DataFetchingEnvironment dfe){
        Object object =DgsContext.getCustomContext(dfe);
        if (object instanceof GraphQLException){
            throw (GraphQLException)object;
        }
        else {
            return (AccountEntity)object;
        }
    }
}
