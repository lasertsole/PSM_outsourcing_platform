package com.psm.custom;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class AuthContextBuilder implements DgsCustomContextBuilderWithRequest {
    static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public Object build(@Nullable Map map, @Nullable HttpHeaders httpHeaders, @Nullable WebRequest webRequest) {
        AuthContext authContext = new AuthContext();
        if (!httpHeaders.containsKey(AUTHORIZATION_HEADER)){
            return authContext;
        }
        System.out.println(httpHeaders);
        return null;
    }
}
