package com.psm.controller;

import com.psm.service.StaticResourceService;
import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StaticResourceController {

    @Autowired
    StaticResourceService staticResourceService;

    /*获取封面函数*/
    @RequestMapping(value = "/getFrontCover", method = RequestMethod.GET)
    public Result<?> getFrontCover(){
        return staticResourceService.getFrontCover();
    }
}
