package com.psm.type;

import com.psm.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String ID;
    private String phoneNumber;
    private String password;
    private String userName;
    private String profile;
    private String salt;
    private String token;
    private String status;
    private String commentNum;
}
