package com.psm.type;

import com.psm.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String status;
    private String phoneNumber;
    private String userName;
    private String profile;
    private String token;
}
