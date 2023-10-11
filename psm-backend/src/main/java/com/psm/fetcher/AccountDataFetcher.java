package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.psm.mapper.AccountMapper;
import com.psm.type.Account;
import com.psm.type.AccountInput;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@DgsComponent
public class AccountDataFetcher {
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;//用于加密密码

    public AccountDataFetcher(AccountMapper accountMapper, PasswordEncoder passwordEncoder){//springboot自动注入依赖
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @DgsMutation
    public Account register(@InputArgument AccountInput acountInput){
        String salt = String.valueOf(Math.abs(new Date().toString().hashCode()));
        String password = acountInput.getPassword();
        password = passwordEncoder.encode(password+salt);
        String phoneNumber = acountInput.getPhoneNumber();
        String token = passwordEncoder.encode(phoneNumber+password+new Date().toString());
        String userName = "新用户"+String.valueOf(Math.abs((salt+salt+token).hashCode()));
        String profile = "/images/Profile/defaultProfile.jpg";
        int status = 1;
        try{
            accountMapper.addNewAccount(phoneNumber, password, userName, profile, salt, token);
            Account account = new Account(String.valueOf(status), phoneNumber, userName, profile, token);
            return account;
        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
    }
}
