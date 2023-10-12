package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.psm.custom.GraphQLException;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

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
}
