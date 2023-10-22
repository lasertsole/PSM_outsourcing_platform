package com.psm.fetcher;

import com.netflix.graphql.dgs.InputArgument;
import com.psm.type.ShowcaseBox;
import com.psm.type.ShowcaseBoxParamsInput;
import com.psm.type.ShowcaseDetailBox;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:24
 * @Description:橱窗操作抽象类
 */
public interface ShowcaseFetcher {
    /**
     * @description:获取概要橱窗盒子
     * @Param showcaseBoxParams: 查询概要橱窗盒子的配置参数
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.util.List<com.psm.type.ShowcaseBox>: 按参数概返回的概要橱窗盒子
     */
    public List<ShowcaseBox> getShowcaseBoxes(ShowcaseBoxParamsInput showcaseBoxParams, String token);

    /**
     * @description:获取详情橱窗盒子
     * @Param ID: 详情橱窗盒子的唯一识别码
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: com.psm.type.ShowcaseDetailBox:详情橱窗盒子的信息
     */
    public ShowcaseDetailBox getShowcaseBoxDetail(String ID, String token);
}
