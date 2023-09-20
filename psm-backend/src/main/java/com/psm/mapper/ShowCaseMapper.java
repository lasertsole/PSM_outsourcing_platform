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

    @Select("<script> select * from accountinfo inner join showcaseBox on accountinfo.id=showcaseBox.author_id where token=#{token} and" +
            " primarySort=#{primarySort} and lastSort=#{lastSort} and isIdle=#{isIdle} and canQuicky=#{canQuicky} order by " +
            "<when test='sortWay == 0'> showcaseBox.modify_time </when>" +
            "<when test='sortWay == 1'> showcaseBox.commentNum </when>" +
            " desc limit 50 </script>")
    public List<ShowcaseBoxEntity> getShowcaseBoxes(String token, String primarySort, String lastSort, String isIdle, String canQuicky, String sortWay);


}
