package com.psm.fetcher;

import java.util.List;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:27
 * @Description:静态资源操作抽象类
 */
public interface StaticResourceFetcher {
    /**
     * @description:获取首页封面
     * @return: java.util.List<java.lang.String>:首页封面路径的数组
     */
    public List<String> FrontCover();
}
