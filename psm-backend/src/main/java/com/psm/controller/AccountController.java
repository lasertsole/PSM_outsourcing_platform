package com.psm.controller;

import com.psm.entity.AccountEntity;
import com.psm.utils.Result;
import com.psm.vo.AccountVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.psm.ato.accountAto;
import com.psm.service.AccountService;

import java.util.List;

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
        AccountVo accountVo = new AccountVo();
        return accountService.fasterLogin(token);
    }
}