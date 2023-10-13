package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.psm.custom.GraphQLException;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import com.psm.type.Account;
import com.psm.utils.IdentiUitil;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@DgsComponent
public class AccountFetcher {
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;//用于加密密码

    public AccountFetcher(AccountMapper accountMapper, PasswordEncoder passwordEncoder){//springboot自动注入依赖
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @DgsMutation
    public Account register(@InputArgument String phoneNumber, @InputArgument String password){
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

            Account account = new Account();
            BeanUtils.copyProperties(accountEntity,account);
            return account;
        }catch (Exception e){
            throw new GraphQLException("409", "手机号已被注册");
        }
    }

    @DgsQuery
    public Account login(@InputArgument String phoneNumber, @InputArgument String password){
        AccountEntity accountEntity;
        try {
            accountEntity = accountMapper.findByPhone(phoneNumber).get(0);
        } catch (Exception e) {
            throw new GraphQLException("404", "账号不存在，登录失败");
        }

        String salt = accountEntity.getSalt();
        if(passwordEncoder.matches(password+salt, accountEntity.getPassword())){
            switch (Integer.parseInt(accountEntity.getStatus())){
                case 2:
                    throw new GraphQLException("403", "账号已被封禁");
                case 3:
                    throw new GraphQLException("410", "账号已注销");
                default:break;
            };

            Account account = new Account();
            BeanUtils.copyProperties(accountEntity,account);
            return account;
        }else{
            throw new GraphQLException("401", "密码错误，登录失败");
        }
    }

    @DgsQuery
    public Account fasterLogin(DataFetchingEnvironment dfe){
        Object object =DgsContext.getCustomContext(dfe);
        if (object instanceof GraphQLException){
            throw (GraphQLException)object;
        }
        else {

            Account account = new Account();
            BeanUtils.copyProperties((AccountEntity)object,account);
            return account;
        }
    }

    @DgsMutation
    public Boolean changeUserName(@InputArgument String userName, DataFetchingEnvironment dfe){
        Object object = IdentiUitil.IdentiToken(dfe);//判断是否为登录状态

        try {
            int result = 0;
            AccountEntity accountEntity = (AccountEntity)object;
            result = accountMapper.updateUserName(accountEntity.getToken(), userName);
            if(result!=1){
                throw new GraphQLException("304", "修改名字未成功");
            }
            else{
                return true;
            }
        }catch (Exception e){
            throw new GraphQLException("500","修改名字时发生程序错误");
        }
    }

    @DgsMutation
    public Boolean changeUserPhoneNumber(@InputArgument String userPhoneNumber, DataFetchingEnvironment dfe){
        Object object = IdentiUitil.IdentiToken(dfe);//判断是否为登录状态

        try {
            int result = 0;
            AccountEntity accountEntity = (AccountEntity)object;

            result = accountMapper.updatePhoneNumber(accountEntity.getToken(), userPhoneNumber);
            if(result!=1){
                throw new GraphQLException("304", "修改手机号未成功");
            }
            else{
                return true;
            }
        }catch (Exception e){
            throw new GraphQLException("500","修改手机号时发生错误");
        }
    }

    @DgsMutation
    public Boolean changeUserPassword(@InputArgument String userPassword, DataFetchingEnvironment dfe){
        Object object = IdentiUitil.IdentiToken(dfe);//判断是否为登录状态

        try {
            int result = 0;
            List<AccountEntity> list;
            String salt;
            String oldPassword;
            String newPassword;
            AccountEntity accountEntity = (AccountEntity)object;
            String token = accountEntity.getToken();

            list = accountMapper.findByToken(token);
            salt = list.get(0).getSalt();
            oldPassword = list.get(0).getPassword();
            newPassword = String.valueOf(Math.abs((userPassword+salt).hashCode()));

            if(oldPassword.equals(newPassword)){
                throw new GraphQLException("304", "新密码不能和旧密码相同");
            }
            else{
                result = accountMapper.updatePassword(token, newPassword);
                if(result!=1){
                    throw new GraphQLException("304", "修改密码未成功");
                }
                else{
                    return true;
                }
            }
        }catch (Exception e){
            throw new GraphQLException("500", "修改密码时发生程序错误");
        }
    }
}
