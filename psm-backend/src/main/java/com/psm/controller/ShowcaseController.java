package com.psm.controller;
import com.psm.service.ShowcaseService;
import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowcaseController {
    @Autowired
    ShowcaseService showcaseService;

    /**获取橱窗盒子摘要**/
    @RequestMapping(value = "/showcase/getShowcaseBoxes", method = RequestMethod.GET)
    public Result<?> getShowcaseBoxes(@RequestHeader("token") String token){
        return showcaseService.getShowcaseBoxes(token);
    }
}
