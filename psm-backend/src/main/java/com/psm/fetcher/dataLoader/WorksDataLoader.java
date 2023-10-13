package com.psm.fetcher.dataLoader;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.psm.mapper.ShowCaseMapper;
import com.psm.type.ItemBox;
import org.dataloader.BatchLoader;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "works")
public class WorksDataLoader implements BatchLoader<String, String> {
    private ShowCaseMapper showCaseMapper;
    public WorksDataLoader(ShowCaseMapper showCaseMapper){this.showCaseMapper = showCaseMapper;};//springboot自动注入依赖

    public String FormatToJson(List<ItemBox> itemBoxList) {
        Boolean startFlag = true;
        StringBuilder itemBoxToString = new StringBuilder();
        itemBoxToString.append("[");
        for(ItemBox item:itemBoxList){
            if (!startFlag){
                itemBoxToString.append(",");
            }
            startFlag=false;

            itemBoxToString.append("{\"ID\":\"")
                    .append(item.getID())
                    .append("\",\"authorID\":\"")
                    .append(item.getAuthorID())
                    .append("\",\"price\":\"")
                    .append(item.getPrice())
                    .append("\",\"type\":\"")
                    .append(item.getType())
                    .append("\",\"imgPath\":\"")
                    .append(item.getImgPath())
                    .append("\",\"videoPath\":\"")
                    .append(item.getVideoPath())
                    .append("\",\"abstractInfo\":\"")
                    .append(item.getAbstractInfo())
                    .append("\",\"modifyTime\":\"")
                    .append(item.getModifyTime())
                    .append("\"}");
        }
        itemBoxToString.append("]");
        return itemBoxToString.toString();
    }

    @Override
    public CompletionStage<List<String>> load(List<String> authorIDs) {
        List<ItemBox> itemBoxesList = showCaseMapper.getBatchItemBoxes(authorIDs);//从服务器得到查询结果
        LinkedHashMap<String, List<ItemBox>> itemBoxLinkedHashMap  = new LinkedHashMap<>();//用于存储ItemBox中authorID属性的数组，用于给itemBoxesList去重

        //遍历itemBoxesList,将里面的ItemBox分配进authorID对应的位置
        itemBoxesList.forEach((item)->{
            if(!itemBoxLinkedHashMap.containsKey(item.getAuthorID())){
                List<ItemBox> subItemBoxesList = new ArrayList<>();
                subItemBoxesList.add(item);
                itemBoxLinkedHashMap.put(item.getAuthorID(), subItemBoxesList);
            }
            else{
                itemBoxLinkedHashMap.get(item.getAuthorID()).add(item);
            }
        });

        //遍历itemBoxesList
        Iterator<Map.Entry<String, List<ItemBox>>> iterator = itemBoxLinkedHashMap.entrySet().iterator();//获得itemBoxesList的迭代器
        List<String> jsonList = new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<String, List<ItemBox>> entry = iterator.next();
            jsonList.add(FormatToJson(entry.getValue()));
        }

        return CompletableFuture.supplyAsync(
                () -> jsonList
        );
    }
}
