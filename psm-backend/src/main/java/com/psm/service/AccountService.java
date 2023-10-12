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
            return Result.error("500","修改手机号时发生错误:"+e.toString());
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
            return Result.error("500", "修改密码时发生程序错误:"+e.toString());
        }
    }
}
