package com.psm.custom;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import org.jetbrains.annotations.Nullable;
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

        //判断请求头是否含有Token字段
        if (!httpHeaders.containsKey(AUTHORIZATION_HEADER)){
            return new GraphQLException("401", "用户未提供token");
        }

        String token = httpHeaders.getFirst(AUTHORIZATION_HEADER);
        List<AccountEntity> list = accountMapper.findByToken(token);

        if(list.size()==0){
            return new GraphQLException("401", "无效token");
        }
        else if (!list.get(0).getStatus().equals("1")) {
            return new GraphQLException("403", "账号不可用");
        }

        return list.get(0);
    }
}
