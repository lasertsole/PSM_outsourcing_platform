package com.psm.custom;

import lombok.Data;
import com.psm.entity.AccountEntity;

@Data
public class AuthContext {
    private AccountEntity accountEntity;
    private boolean tokenInvaild;

    public void ensureAuthenticated(){
        if (tokenInvaild) throw new RuntimeException("令牌无效!");
        if (accountEntity == null) throw new RuntimeException("未登录，请先登录!");
    }
}
