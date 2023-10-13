package com.psm.mapper;

import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import com.psm.type.ItemBox;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShowCaseMapper {

    @Select("<script> select showcaseBox.ID as ID,userName,profile,honor,authorBrief,showcaseBox.authorID as authorID,showcaseBox.modifyTime" +
            " as modifyTime,commentNum,primarySort,lastSort,isIdle,canQuicky" +
            " from accountinfo inner join showcaseBox on accountinfo.ID=showcaseBox.authorID" +
            " where primarySort=#{primarySort} and lastSort=#{lastSort} and accountinfo.status=1" +
            "<when test='isIdle'> and isIdle=#{isIdle}</when>" +
            "<when test='canQuicky'> and canQuicky=#{canQuicky} </when>" +
            "order by" +
            "<when test='sortWay == 0'> showcaseBox.modifyTime </when>" +
            "<when test='sortWay == 1'> accountInfo.commentNum </when>" +
            " desc limit 50 </script>")

    public List<ShowcaseBoxEntity> getShowcaseBoxes(String primarySort, String lastSort, Boolean isIdle, Boolean canQuicky, String sortWay);

    @Select("select itemBox.ID, itemBox.authorID, itemBox.price, itemBox.imgPath, itemBox.videoPath, itemBox.abstractInfo, itemBox.modifyTime," +
            "itemBox.mainInfo, accountInfo.profile, accountInfo.userName, accountInfo.commentNum, showcaseBox.primarySort, showcaseBox.lastSort," +
            "showcaseBox.isIdle, showcaseBox.canQuicky" +
            " from itemBox inner join accountInfo on itemBox.authorID=accountInfo.ID inner join showcaseBox on accountInfo.ID=showcaseBox.authorID" +
            " where itemBox.ID=#{ID} and itemBox.type=0 and accountinfo.status=1")
    public List<showcaseDetailEntity> getShowcaseBoxDetail(String ID);

    @Select("<script>" +
                "select * from itemBox where itemBox.authorID in " +
                "<foreach item='authorID' collection='authorIDs' open='(' separator=',' close=')'>" +
                    "#{authorID}" +
                "</foreach>" +
                "order by  "+
                "<foreach item='authorID' collection='authorIDs' open='field(authorID,' separator=',' close=')'>" +
                    "#{authorID}" +
                "</foreach>" +
            "</script>")
    public List<ItemBox> getBatchItemBoxes(@Param("authorIDs")List<String> authorIDs);
}
