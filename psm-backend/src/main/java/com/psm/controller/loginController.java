package com.psm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    @Autowired
    private Environment env;
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version(){
        return "喵剪辑后端"+env.getProperty("version");
    }
}