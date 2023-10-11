package com.psm.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import com.psm.utils.Result;
import com.psm.vo.AccountVo;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Component
public class AuthContextBuilder implements DgsCustomContextBuilderWithRequest {
    //请求头的认证字段为Token
    static final String AUTHORIZATION_HEADER = "Token";

    private final AccountMapper accountMapper;

    public AuthContextBuilder(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    //fetcher前认证
    @Override
    public Object build(@Nullable Map map, @Nullable HttpHeaders httpHeaders, @Nullable WebRequest webRequest) {
        AuthContext authContext = new AuthContext();

        //判断请求头是否含有Token字段
        if (!httpHeaders.containsKey(AUTHORIZATION_HEADER)){
            authContext.setTokenInvaild(true);
            return authContext;
        }

        String token = httpHeaders.getFirst(AUTHORIZATION_HEADER);
        List<AccountEntity> list = accountMapper.findByToken(token);

        if(list.size()==0||list.get(0).getStatus()!="1"){
            authContext.setTokenInvaild(true);
            return authContext;
        }

        System.out.println(token);

        return token;
    }
}
