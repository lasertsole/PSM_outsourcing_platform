package com.psm.controller;

import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.psm.ato.accountAto;
import com.psm.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result<?> login(@RequestBody accountAto ato){
        return accountService.login(ato.getPhoneNumber(), ato.getPassword());
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Result<?> register(@RequestBody accountAto ato){
        return accountService.register(ato.getPhoneNumber(), ato.getPassword());
    }

    @RequestMapping(value = "/user/fasterLogin", method = RequestMethod.GET)
    public Result<?> fasterLogin(@RequestHeader("token") String token){
        return accountService.fasterLogin(token);
    }

    @RequestMapping(value = "/user/changeUserName", method = RequestMethod.POST)
    public Result<?> changeUserName(@RequestHeader("token") String token, @RequestBody accountAto ato){
        return accountService.changeUserName(token, ato.getUserName());
    }

    @RequestMapping(value = "/user/changeUserPhoneNumber", method = RequestMethod.POST)
    public Result<?> changeUserPhoneNumber(@RequestHeader("token") String token, @RequestBody accountAto ato){
        return accountService.changeUserPhoneNumber(token, ato.getPhoneNumber());
    }

    @RequestMapping(value = "/user/changeUserPassword", method = RequestMethod.POST)
    public Result<?> changeUserPassword(@RequestHeader("token") String token, @RequestBody accountAto ato){
        return accountService.changeUserPassword(token, ato.getPassword());
    }
}