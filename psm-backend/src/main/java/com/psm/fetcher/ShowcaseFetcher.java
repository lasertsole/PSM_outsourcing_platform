package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.psm.custom.GraphQLException;
import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import com.psm.mapper.ShowCaseMapper;
import com.psm.type.*;
import com.psm.utils.IdentiUitil;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ShowcaseFetcher {
    private ShowCaseMapper showCaseMapper;

    public ShowcaseFetcher(ShowCaseMapper showCaseMapper){//springboot自动注入依赖
        this.showCaseMapper = showCaseMapper;
    }

    @DgsQuery
    public List<ShowcaseBox> getShowcaseBoxes(@InputArgument ShowcaseBoxParamsInput showcaseBoxParams, DataFetchingEnvironment dfe){
        IdentiUitil.IdentiToken(dfe);//判断是否为登录状态

        try{
            List<ShowcaseBoxEntity> showcaseBoxEntityList = showCaseMapper.getTestBoxes(showcaseBoxParams.getPrimarySort(), showcaseBoxParams.getLastSort(),
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
            throw new GraphQLException("500",e.toString());
        }
    }

    @DgsQuery
    public ShowcaseDetailBox getShowcaseBoxDetail(@InputArgument String ID, DataFetchingEnvironment dfe){
        IdentiUitil.IdentiToken(dfe);//判断是否为登录状态

        try {
            List<showcaseDetailEntity> list = showCaseMapper.getShowcaseBoxDetail(ID);
            ShowcaseDetailBox showcaseDetailBox = new ShowcaseDetailBox();
            BeanUtils.copyProperties(list.get(0),showcaseDetailBox);
            return showcaseDetailBox;
        }
        catch (Exception e){
            throw new GraphQLException("500","获取橱窗详情时发生错误");
        }
    }

    @DgsData(parentType = "ShowcaseBox", field = "works")
    public CompletableFuture<String> works(DataFetchingEnvironment dfe){
        ShowcaseBox showcaseBox = dfe.getSource();

        DataLoader<String, String> dataLoader = dfe.getDataLoader("works");
        return dataLoader.load(showcaseBox.getAuthorID());
    }
}
