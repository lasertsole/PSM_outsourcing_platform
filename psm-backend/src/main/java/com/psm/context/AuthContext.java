package com.psm.context;

import lombok.Data;
import com.psm.entity.AccountEntity;

@Data
public class AuthContext {
    private AccountEntity accountEntity;
    private boolean tokenInvaild;//是否是无效令牌
}
