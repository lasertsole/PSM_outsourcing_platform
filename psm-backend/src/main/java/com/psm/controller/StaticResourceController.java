package com.psm.controller;

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
    private Environment env;
    private String fileRoot = "static";
    private String fileRelativePosition = "/images/Carousel/";
    private String filesPath = ClassUtils.getDefaultClassLoader().getResource(fileRoot+fileRelativePosition).getPath();
    @RequestMapping(value = "/getFrontCover", method = RequestMethod.GET)
    public List<String> getFrontCover(){
        File file = new File(filesPath);
        String [] names = file.list();
        List<String> resultNames = new ArrayList<>();
        for (String string : names) {
            resultNames.add(fileRelativePosition+string);
        }
        return resultNames;
    }
}
