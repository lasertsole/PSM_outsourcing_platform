package com.psm.service;

import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaticResourceService {
    @Autowired
    private Environment env;
    private String fileRoot = "static";
    private String fileRelativePosition = "/images/Carousel/";
    private String filesPath = ClassUtils.getDefaultClassLoader().getResource(fileRoot+fileRelativePosition).getPath();
    @RequestMapping(value = "/getFrontCover", method = RequestMethod.GET)
    public Result<?> getFrontCover(){
        File file = new File(filesPath);
        String [] names = file.list();
        try{
            List<String> resultNames = new ArrayList<>();
            for (String string : names) {
                resultNames.add(fileRelativePosition+string);
            }
            return Result.success(resultNames, "获取封面成功");
        }
        catch (Exception e){
            return Result.error("404", "获取封面失败");
        }
    }
}
