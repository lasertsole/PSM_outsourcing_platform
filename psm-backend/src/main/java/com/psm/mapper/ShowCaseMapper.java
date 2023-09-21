package com.psm.mapper;

import com.psm.entity.ShowcaseBoxEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShowCaseMapper {

    @Select("<script> select showcaseBox.id as id,userName,profile,honor,author_brief,showcaseBox.author_id as author_id," +
            "works,showcaseBox.modify_time as modify_time,commentNum,primarySort,lastSort,isIdle,canQuicky,price,type,imgPath," +
            "videoPath,abstractInfo from accountinfo inner join showcaseBox on accountinfo.id=showcaseBox.author_id" +
            " left join itemBox on accountinfo.id=itemBox.author_id" +
            " where primarySort=#{primarySort} and lastSort=#{lastSort}" +
            "<when test='isIdle'> and isIdle=#{isIdle}</when>" +
            "<when test='canQuicky'> and canQuicky=#{canQuicky} </when>" +
            "order by" +
            "<when test='sortWay == 0'> showcaseBox.modify_time </when>" +
            "<when test='sortWay == 1'> showcaseBox.commentNum </when>" +
            " desc limit 50 </script>")
    public List<ShowcaseBoxEntity> getShowcaseBoxes(String primarySort, String lastSort, Boolean isIdle, Boolean canQuicky, String sortWay);
}
