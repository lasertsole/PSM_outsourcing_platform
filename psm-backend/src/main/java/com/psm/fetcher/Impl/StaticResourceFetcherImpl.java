package com.psm.fetcher.Impl;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.psm.custom.GraphQLException;
import com.psm.fetcher.ShowcaseFetcher;
import com.psm.fetcher.StaticResourceFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:27
 * @Description:静态资源操作实体类
 */
@DgsComponent
public class StaticResourceFetcherImpl implements StaticResourceFetcher {
    private String fileRoot = "static";
    private String fileRelativePosition = "/images/Carousel/";
    private String filesPath = ClassUtils.getDefaultClassLoader().getResource(fileRoot+fileRelativePosition).getPath();

    /**
     * @author: moye
     * @date: 2023/10/22 18:20
     * @description:获取首页封面
     * @return: java.util.List<java.lang.String>:首页封面路径的数组
     */
    @DgsQuery
    public List<String> FrontCover() throws GraphQLException {
        try{
            File file = new File(filesPath);
            String [] names = file.list();
            List<String> resultNames = new ArrayList<>();
            for (String string : names){
                resultNames.add(fileRelativePosition+string);
            }
            return resultNames;
        }
        catch (Exception e){
            throw new GraphQLException("20042", "获取封面时发生错误");
        }
    }
}
