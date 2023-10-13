package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.psm.custom.GraphQLException;
import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import com.psm.mapper.ShowCaseMapper;
import com.psm.type.ShowcaseBox;
import com.psm.type.ShowcaseBoxParamsInput;
import com.psm.type.ShowcaseDetailBox;
import com.psm.type.ItemBox;
import com.psm.utils.IdentiUitil;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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
            /**为减少查询数据库的IO操作，请求数据库时把account表、showcaseBox表、itemBox表的信息通过连接请求回来，实现请求一次数据库便获得全部橱窗盒子信息**/
            List<ShowcaseBoxEntity> showcaseBoxEntityList = showCaseMapper.getShowcaseBoxes(showcaseBoxParams.getPrimarySort(), showcaseBoxParams.getLastSort(),
                    showcaseBoxParams.getIsIdle(), showcaseBoxParams.getCanQuicky(), showcaseBoxParams.getSortWay());;

            /**由于一次性全部请求回来的信息有大量冗余，需要进行加工处理才能返回给前端**/
            List<String> authorIDList = new ArrayList<>();//用于存储ShowcaseBoxEntity中authorID属性的数组，用于给showcaseBoxEntityList去重
            List<ShowcaseBox> showcaseBoxList = new ArrayList<>();//用于返回前端的结果数组


            showcaseBoxEntityList.forEach(item ->{
                /*给showcaseBoxEntityList去重*/
                if(!authorIDList.contains(item.getAuthorID())){//判断authorID是否在authorIDList内
                    authorIDList.add(item.getAuthorID());//若authorIDList没有该authorID，则加入数组

                    ShowcaseBox showcaseBox = new ShowcaseBox();
                    BeanUtils.copyProperties(item,showcaseBox);
                    showcaseBox.setWorks("[]");
                    showcaseBoxList.add(showcaseBox);
                };

                int index = authorIDList.indexOf(item.getAuthorID());

                ItemBox itemBox = new ItemBox();
                BeanUtils.copyProperties(item,itemBox);

                /*将works加工成json格式*/
                StringBuffer itemBoxToString = new StringBuffer();
                itemBoxToString.append("{\"ID\":\"")
                        .append(item.getWorkID())
                        .append("\",\"authorID\":\"")
                        .append(itemBox.getAuthorID())
                        .append("\",\"price\":\"")
                        .append(itemBox.getPrice())
                        .append("\",\"type\":\"")
                        .append(itemBox.getType())
                        .append("\",\"imgPath\":\"")
                        .append(itemBox.getImgPath())
                        .append("\",\"videoPath\":\"")
                        .append(itemBox.getVideoPath())
                        .append("\",\"abstractInfo\":\"")
                        .append(itemBox.getAbstractInfo())
                        .append("\",\"modifyTime\":\"")
                        .append(item.getWorkModifyTime())
                        .append("\"}");

                ShowcaseBox base = showcaseBoxList.get(index);
                String baseWorks = base.getWorks();

                if(baseWorks.charAt(baseWorks.length()-2)=='['){
                    StringBuilder sb = new StringBuilder(baseWorks);
                    sb.insert(baseWorks.length()-1,itemBoxToString.toString());
                    base.setWorks(sb.toString());
                }
                else{
                    StringBuilder sb = new StringBuilder(baseWorks);
                    sb.insert(baseWorks.length()-1,","+itemBoxToString.toString());
                    base.setWorks(sb.toString());
                }
            });

            return showcaseBoxList;
        }
        catch (Exception e){
            throw new GraphQLException("500","获取橱窗盒子时发生错误");
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
}
