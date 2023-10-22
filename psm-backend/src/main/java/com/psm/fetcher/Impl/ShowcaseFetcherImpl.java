package com.psm.fetcher.Impl;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.psm.custom.GraphQLException;
import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import com.psm.fetcher.Aop.annotation.IdentifyToken;
import com.psm.fetcher.ShowcaseFetcher;
import com.psm.mapper.ShowCaseMapper;
import com.psm.type.*;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:24
 * @Description:橱窗操作实体类
 */
@DgsComponent
public class ShowcaseFetcherImpl implements ShowcaseFetcher {
    @Autowired
    private ShowCaseMapper showCaseMapper;

    /**
     * @author: moye
     * @date: 2023/10/22 18:15
     * @description:获取概要橱窗盒子
     * @Param showcaseBoxParams: 查询概要橱窗盒子的配置参数
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.util.List<com.psm.type.ShowcaseBox>: 按参数概返回的概要橱窗盒子
     */
    @DgsQuery
    @IdentifyToken
    public List<ShowcaseBox> getShowcaseBoxes(@InputArgument ShowcaseBoxParamsInput showcaseBoxParams, @RequestHeader("Token") String token){
        try{
            List<ShowcaseBoxEntity> showcaseBoxEntityList = showCaseMapper.getShowcaseBoxes(showcaseBoxParams.getPrimarySort(), showcaseBoxParams.getLastSort(),
                    showcaseBoxParams.getIsIdle(), showcaseBoxParams.getCanQuicky(), showcaseBoxParams.getSortWay());

            List<ShowcaseBox> showcaseBoxList = new ArrayList<>();
            showcaseBoxEntityList.stream().forEach(
                    (item)->{
                        ShowcaseBox showcaseBox = new ShowcaseBox();
                        BeanUtils.copyProperties(item,showcaseBox);
                        showcaseBoxList.add(showcaseBox);
                    }
            );

            return showcaseBoxList;
        }
        catch (Exception e){
            throw new GraphQLException("20042",e.toString());
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:17
     * @description:获取详情橱窗盒子
     * @Param ID: 详情橱窗盒子的唯一识别码
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: com.psm.type.ShowcaseDetailBox:详情橱窗盒子的信息
     */
    @DgsQuery
    @IdentifyToken
    public ShowcaseDetailBox getShowcaseBoxDetail(@InputArgument String ID, @RequestHeader("Token") String token){
        try {
            List<showcaseDetailEntity> list = showCaseMapper.getShowcaseBoxDetail(ID);
            ShowcaseDetailBox showcaseDetailBox = new ShowcaseDetailBox();
            BeanUtils.copyProperties(list.get(0),showcaseDetailBox);
            return showcaseDetailBox;
        }
        catch (Exception e){
            throw new GraphQLException("20042","获取橱窗详情时发生错误");
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:18
     * @description:概要橱窗盒子附带的
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.util.concurrent.CompletableFuture<java.lang.String>:详情橱窗盒子的信息的概要信息组
     */
    @DgsData(parentType = "ShowcaseBox", field = "works")
    public CompletableFuture<String> works(DataFetchingEnvironment dfe){
        ShowcaseBox showcaseBox = dfe.getSource();

        DataLoader<String, String> dataLoader = dfe.getDataLoader("works");
        return dataLoader.load(showcaseBox.getAuthorID());
    }
}
