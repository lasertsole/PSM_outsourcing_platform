package com.psm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo implements Serializable {
    private String status;
    private String phoneNumber;
    private String userName;
    private String profile;
    private String token;
}
