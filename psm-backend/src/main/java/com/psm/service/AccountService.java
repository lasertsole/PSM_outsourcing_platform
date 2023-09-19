package com.psm.service;
import com.psm.entity.AccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psm.mapper.AccountMapper;
import com.psm.vo.AccountVo;
import java.util.Date;
import java.util.List;
import com.psm.utils.Result;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Result<?> login(String phoneNumber, String password) {
        AccountEntity accountEntity;
        try {
            accountEntity = accountMapper.findByPhone(phoneNumber).get(0);
        } catch (Exception e) {
            return Result.error("404", "账号不存在，登录失败");
        }

        if(accountEntity.getPassword().equals(String.valueOf(Math.abs((password+accountEntity.getSalt()).hashCode())))){
            switch (Integer.parseInt(accountEntity.getStatus())){
                case 2:
                    return Result.error("403", "账号已被封禁");
                case 3:
                    return Result.error("410", "账号已注销");
                default:break;
            };

            AccountVo accountVo = new AccountVo();
            BeanUtils.copyProperties(accountEntity, accountVo);
            return Result.success(accountVo, "登录成功");
        }else{
            return Result.error("401", "密码错误，登录失败");
        }
    }

    public Result<?> register(String phoneNumber, String password) {
        String salt = String.valueOf(Math.abs(new Date().toString().hashCode()));
        password = String.valueOf(Math.abs((password+salt).hashCode()));
        String token = String.valueOf(Math.abs((phoneNumber+password+new Date().toString().hashCode()).hashCode()));
        String userName = "新用户"+String.valueOf(Math.abs((salt+salt+token).hashCode()));
        String profile = "/images/defaultProfile/defaultProfile.jpg";
        int status = 1;
        try{
            accountMapper.addNewAccount(phoneNumber, password, userName, profile, salt, token);
            AccountVo accountVo = new AccountVo(String.valueOf(status), phoneNumber, userName, profile, token);
            return Result.success(accountVo,"注册成功");
        }catch (Exception e){
            return Result.error("409","注册失败，用户已存在");
        }
    }

    public Result<?> fasterLogin(String token) {
        List<AccountEntity> list;
        try{
            list = accountMapper.findByToken(token);
            if (list.size()==0){
                return Result.error("404","token对应的用户不存在");
            }
            else if(list.get(0).getStatus()=="2"){
                return Result.error("403","账号封禁中");
            }
            else if(list.get(0).getStatus()=="2"){
                return Result.error("410","账号已注销");
            }
            else{
                AccountVo accountVo = new AccountVo();
                BeanUtils.copyProperties(list.get(0), accountVo);
                return Result.success(accountVo,"快速登录成功");
            }
        }catch (Exception e){
            return Result.error("500","登录时发生错误");
        }
    }

    public Result<?> changeUserName(String token, String userName) {
        AccountVo accountVo = new AccountVo();
        int result = 0;

        try {
            result = accountMapper.updateUserName(token, userName);
            if(result!=1){
                return Result.error("304", "修改名字未成功");
            }
            else{
                return Result.success("修改名字成功");
            }
        }catch (Exception e){
            return Result.error("500","修改名字时发生错误。");
        }
    }

    public Result<?> changeUserPhoneNumber(String token, String phoneNumber) {
        int result = 0;

        try {
            result = accountMapper.updatePhoneNumber(token, phoneNumber);
            if(result!=1){
                return Result.error("304", "修改手机号未成功");
            }
            else{
                return Result.success("修改成功");
            }
        }catch (Exception e){
            return Result.error("500","修改手机号时发生错误。");
        }
    }

    public Result<?> changeUserPassword(String token, String password) {
        int result = 0;
        List<AccountEntity> list;
        String salt;
        String oldPassword;
        String newPassword;
        try {
            list = accountMapper.findByToken(token);
            salt = list.get(0).getSalt();
            oldPassword = list.get(0).getPassword();
            newPassword = String.valueOf(Math.abs((password+salt).hashCode()));

            if(oldPassword.equals(newPassword)){
                return Result.error("304", "新密码不能和旧密码相同");
            }
            else{
                result = accountMapper.updatePassword(token, newPassword);
                if(result!=1){
                    return Result.error("304", "修改密码未成功");
                }
                else{
                    return Result.success("修改成功");
                }
            }
        }catch (Exception e){
            return Result.error("500", "修改密码时发生程序错误");
        }
    }
}