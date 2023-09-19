package com.psm.controller;
import com.psm.service.ShowcaseService;
import com.psm.utils.Result;
import com.psm.ato.ShowcaseBoxAto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowcaseController {
    @Autowired
    ShowcaseService showcaseService;

    /**获取橱窗盒子摘要**/
    @RequestMapping(value = "/showcase/getShowcaseBoxes", method = RequestMethod.GET)
    public Result<?> getShowcaseBoxes(@RequestHeader("token") String token, @RequestParam String primarySort, String middleSort, String lastSort, String isIdle, String canQuicky){
        ShowcaseBoxAto showcaseBoxAto = new ShowcaseBoxAto(primarySort, middleSort, lastSort, isIdle, canQuicky);
        return showcaseService.getShowcaseBoxes(token, showcaseBoxAto);
    }
}
