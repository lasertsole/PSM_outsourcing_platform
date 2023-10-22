package com.psm.fetcher;

import com.netflix.graphql.dgs.InputArgument;
import com.psm.type.Account;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:14
 * @Description:用户账户操作的抽象类
 */
public interface AccountFetcher {

    /**
     * @description:用户注册账号
     * @Param phoneNumber: 用户手机号码
     * @Param password: 用户密码
     * @return: com.psm.type.Account: 已注册完成的账号
     */
    public Account register(String phoneNumber, String password);

    /**
     * @Param phoneNumber: 用户手机号码
     * @Param password: 用户密码
     * @return: com.psm.type.Account: 没有异常状态的用户账号
     */
    public Account login(String phoneNumber, String password);

    /**
     * @description:使用token快速登录
     * @Param token: 用户令牌
     * @return: com.psm.type.Account: 没有异常状态的用户账号
     */
    public Account fasterLogin(String token);

    /**
     * @description:更改用户名
     * @Param userName: 修改后的用户名
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    public Boolean changeUserName(String userName, String token);

    /**
     * @description:更改用户手机号
     * @Param userPhoneNumber: 修改后的用户手机号
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    public Boolean changeUserPhoneNumber(String userPhoneNumber, String token);

    /**
     * @description:修改用户密码
     * @Param userPassword: 修改后的用户密码
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    public Boolean changeUserPassword(String userPassword, String token);
}
