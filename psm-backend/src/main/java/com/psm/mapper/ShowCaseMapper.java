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

    @Select("<script> select showcaseBox.id as id,userName,profile,honor,author_brief,author_id,works,showcaseBox.modify_time as" +
            " modify_time,commentNum,primarySort,lastSort,isIdle,canQuicky from accountinfo inner join showcaseBox on " +
            "accountinfo.id=showcaseBox.author_id where primarySort=#{primarySort} and lastSort=#{lastSort} and isIdle=#{isIdle}" +
            " and canQuicky=#{canQuicky} order by " +
            "<when test='sortWay == 0'> showcaseBox.modify_time </when>" +
            "<when test='sortWay == 1'> showcaseBox.commentNum </when>" +
            " desc limit 50 </script>")
    public List<ShowcaseBoxEntity> getShowcaseBoxes(String primarySort, String lastSort, String isIdle, String canQuicky, String sortWay);
}
