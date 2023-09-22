package com.psm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity implements Serializable {
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
