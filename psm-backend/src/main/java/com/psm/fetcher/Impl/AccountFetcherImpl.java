package com.psm.fetcher.Impl;

import com.netflix.graphql.dgs.*;
import com.psm.custom.GraphQLException;
import com.psm.entity.AccountEntity;
import com.psm.fetcher.AccountFetcher;
import com.psm.fetcher.Aop.annotation.IdentifyToken;
import com.psm.mapper.AccountMapper;
import com.psm.type.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import com.psm.utils.MD5;
import java.io.*;
import java.util.*;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher
 * @Author: ChenLan
 * @Date: 2023/10/22 22:14
 * @Description:用户账户操作的实体类
 */
@DgsComponent
public class AccountFetcherImpl implements AccountFetcher {
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;//用于加密密码

    public AccountFetcherImpl(AccountMapper accountMapper, PasswordEncoder passwordEncoder){//springboot自动注入依赖
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:05
     * @description:用户注册账号
     * @Param phoneNumber: 用户手机号码
     * @Param password: 用户密码
     * @return: com.psm.type.Account: 已注册完成的账号
     */
    @DgsMutation
    public Account register(@InputArgument String phoneNumber, @InputArgument String password){
        String salt = String.valueOf(Math.abs(new Date().toString().hashCode()));
        password = passwordEncoder.encode(password+salt);
        String token = passwordEncoder.encode(phoneNumber+password+new Date().toString());
        String userName = "新用户"+String.valueOf(Math.abs((salt+salt+token).hashCode()));
        String profile = "/images/defaultProfile/defaultProfile.jpg";
        int status = 1;
        try{
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setPhoneNumber(phoneNumber);
            accountEntity.setPassword(password);
            accountEntity.setUserName(userName);
            accountEntity.setProfile(profile);
            accountEntity.setSalt(salt);
            accountEntity.setToken(token);
            accountEntity.setStatus(String.valueOf(status));
            accountMapper.addNewAccount(accountEntity);

            Account account = new Account();
            BeanUtils.copyProperties(accountEntity,account);
            return account;
        }catch (Exception e){
            throw new GraphQLException("20012", "手机号已被注册");
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:07
     * @description:用户登录账号
     * @Param phoneNumber: 用户手机号码
     * @Param password: 用户密码
     * @return: com.psm.type.Account: 没有异常状态的用户账号
     */
    @DgsQuery
    public Account login(@InputArgument String phoneNumber, @InputArgument String password){
        AccountEntity accountEntity;
        try {
            accountEntity = accountMapper.findByPhone(phoneNumber).get(0);
        } catch (Exception e) {
            throw new GraphQLException("20012", "账号不存在，登录失败");
        }

        String salt = accountEntity.getSalt();
        if(passwordEncoder.matches(password+salt, accountEntity.getPassword())){
            switch (Integer.parseInt(accountEntity.getStatus())){
                case 2:
                    throw new GraphQLException("20042", "账号已被封禁");
                case 3:
                    throw new GraphQLException("20042", "账号已注销");
                default:break;
            };

            Account account = new Account();
            BeanUtils.copyProperties(accountEntity,account);
            return account;
        }else{
            throw new GraphQLException("20042", "密码错误，登录失败");
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:09
     * @description:使用token快速登录
     * @Param token: 用户令牌
     * @return: com.psm.type.Account: 没有异常状态的用户账号
     */
    @DgsQuery
    public Account fasterLogin(@RequestHeader("Token") String token){
        List<AccountEntity> list = accountMapper.findByToken(token);

        if(list.size()==0){
            throw  new GraphQLException("401", "无效token");
        }
        else if (!list.get(0).getStatus().equals("1")) {
            throw new GraphQLException("403", "账号不可用");
        }

        Account account = new Account();
        BeanUtils.copyProperties(list.get(0), account);
        return account;
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:10
     * @description:更改用户名
     * @Param userName: 修改后的用户名
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    @DgsMutation
    @IdentifyToken
    public Boolean changeUserName(@InputArgument String userName, @RequestHeader("Token") String token){
        try {
            int result = 0;
            result = accountMapper.updateUserName(token, userName);
            if(result!=1){
                throw new GraphQLException("20032", "修改名字未成功");
            }
            else{
                return true;
            }
        }catch (Exception e){
            throw new GraphQLException("20032","修改名字时发生程序错误");
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:11
     * @description:更改用户手机号
     * @Param userPhoneNumber: 修改后的用户手机号
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    @DgsMutation
    @IdentifyToken
    public Boolean changeUserPhoneNumber(@InputArgument String userPhoneNumber, @RequestHeader("Token") String token){
        try {
            int result = 0;
            result = accountMapper.updatePhoneNumber(token, userPhoneNumber);
            if(result!=1){
                throw new GraphQLException("20032", "修改手机号未成功");
            }
            else{
                return true;
            }
        }catch (Exception e){
            throw new GraphQLException("20032", "修改手机号时发生错误");
        }
    }

    /**
     * @author: moye
     * @date: 2023/10/22 18:14
     * @description:修改用户密码
     * @Param userPassword: 修改后的用户密码
     * @Param dfe: fetcher环境变量，不需要传值
     * @return: java.lang.Boolean:修改成功返回true，否则返回异常
     */
    @DgsMutation
    @IdentifyToken
    public Boolean changeUserPassword(@InputArgument String userPassword, @RequestHeader("Token") String token){
        try {
            int result = 0;
            List<AccountEntity> list;
            String salt;
            String oldPassword;
            String newPassword;

            list = accountMapper.findByToken(token);
            salt = list.get(0).getSalt();
            oldPassword = list.get(0).getPassword();
            newPassword = passwordEncoder.encode(userPassword+salt);

            if(oldPassword.equals(newPassword)){
                throw new GraphQLException("20032", "新密码不能和旧密码相同");
            }
            else{
                result = accountMapper.updatePassword(token, newPassword);
                if(result!=1){
                    throw new GraphQLException("20032", "修改密码未成功");
                }
                else{
                    return true;
                }
            }
        }catch (Exception e){
            throw new GraphQLException("20032", "修改密码时发生程序错误");
        }
    }

    @Autowired
    private ResourceUrlProvider resourceUrlProvider;
    private final List<String> legalFormat = Arrays.asList("jpg","png");//合法图片格式
    @DgsMutation
    @IdentifyToken
    public String changeUserProfile(@InputArgument MultipartFile userProfile, @RequestHeader("Token") String token){
        try{
            //file图片校验
            if(userProfile.isEmpty()){
                throw new GraphQLException("20032", "上传的头像为空");
            }
            String originalFilename = userProfile.getOriginalFilename();//原来的图片名
            String nameBack = originalFilename.split("\\.")[1];

            if(!legalFormat.contains(nameBack)){
                throw new GraphQLException("20032", "上传的头像格式错误");
            };

            //file图片重命名
            String ext = "." + nameBack;//得到文件后缀
            // 从MultipartFile中获取输入流
            InputStream in = userProfile.getInputStream();
            // 获取流的MD5值
            String streamMD5= MD5.getStreamMD5(in);//检查上传图片是否和已有图片重复,若重复，md5值相等
            String fileName = streamMD5 + ext;//拼接成完整的文件名

            //上传图片到指定路径
            ApplicationHome applicationHome = new ApplicationHome(this.getClass());
            String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                    "\\src\\main\\resources\\static\\images\\userProfile\\";
            String path = pre + fileName;
            userProfile.transferTo(new File(path));


            //将头像路径写入数据库
            String returnPath = "\\images\\userProfile\\" + fileName;
            int result = accountMapper.updateProfile(token, returnPath);
            if(result!=1){
                throw new GraphQLException("20032", "修改名字未成功");
            }

            return returnPath;
        } catch (Exception e){
            throw new GraphQLException("20032", "修改头像时发生错误");
        }
    }
}
