package com.psm.controller;

import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.psm.ato.AccountAto;
import com.psm.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/user/changeUserPhoneNumber", method = RequestMethod.POST)
    public Result<?> changeUserPhoneNumber(@RequestHeader("token") String token, @RequestBody AccountAto ato){
        return accountService.changeUserPhoneNumber(token, ato.getPhoneNumber());
    }

    @RequestMapping(value = "/user/changeUserPassword", method = RequestMethod.POST)
    public Result<?> changeUserPassword(@RequestHeader("token") String token, @RequestBody AccountAto ato){
        return accountService.changeUserPassword(token, ato.getPassword());
    }
}