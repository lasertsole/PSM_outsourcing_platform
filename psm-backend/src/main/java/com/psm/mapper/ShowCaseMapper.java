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

    @Select("select * from accountinfo inner join showcaseBox on accountinfo.id=showcaseBox.author_id where token=#{token} and" +
            " primarySort=#{primarySort} and middleSort=#{middleSort} and lastSort=#{lastSort} and isIdle=#{isIdle} and " +
            "canQuicky=#{canQuicky} order by showcaseBox.modify_time desc limit 50")
    public List<ShowcaseBoxEntity> getShowcaseBoxes(String token, String primarySort, String middleSort,
                                                    String lastSort, String isIdle, String canQuicky);
}
