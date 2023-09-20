package com.psm.service;
import com.psm.mapper.ShowCaseMapper;
import com.psm.utils.Result;
import com.psm.ato.ShowcaseBoxAto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psm.entity.ShowcaseBoxEntity;

import java.util.List;

@Service
public class ShowcaseService {

    @Autowired
    private ShowCaseMapper showCaseMapper;

    public Result<?> getShowcaseBoxes(String token, ShowcaseBoxAto showcaseBoxAto) {
        try{
            List<ShowcaseBoxEntity> list;
            list = showCaseMapper.getShowcaseBoxes(token, showcaseBoxAto.getPrimarySort(), showcaseBoxAto.getLastSort(),
                    showcaseBoxAto.getIsIdle(), showcaseBoxAto.getCanQuicky(),showcaseBoxAto.getSortWay());
            return Result.success(list,"获取橱窗盒子成功");
        }catch (Exception e){
            return Result.error("304","获取橱窗盒子时发生错误");
        }
    }
}
