package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@DgsComponent
public class StaticResourceFetcher {
    @Autowired
    private Environment env;
    private String fileRoot = "static";
    private String fileRelativePosition = "/images/Carousel/";
    private String filesPath = ClassUtils.getDefaultClassLoader().getResource(fileRoot+fileRelativePosition).getPath();
    @DgsQuery
    public List<String> FrontCover(){
        File file = new File(filesPath);
        String [] names = file.list();
        List<String> resultNames = new ArrayList<>();
        for (String string : names){
            resultNames.add(fileRelativePosition+string);
        }
        return resultNames;
    }
}
