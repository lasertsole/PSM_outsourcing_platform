package com.psm.mapper;

import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShowCaseMapper {

    @Select("<script> select showcaseBox.ID as ID,userName,profile,honor,authorBrief,showcaseBox.authorID as authorID,showcaseBox.modifyTime" +
            " as modifyTime,commentNum,primarySort,lastSort,isIdle,canQuicky,itemBox.ID as workID,price,type,imgPath,videoPath,abstractInfo," +
            "itemBox.modifyTime as workModifyTime from accountinfo inner join showcaseBox on accountinfo.ID=showcaseBox.authorID" +
            " left join itemBox on accountinfo.ID=itemBox.authorID" +
            " where primarySort=#{primarySort} and lastSort=#{lastSort} and itemBox.type=0 and accountinfo.status=1" +
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

}
