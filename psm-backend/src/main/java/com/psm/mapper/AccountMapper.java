package com.psm.mapper;

import com.psm.entity.AccountEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper{
    @Insert("insert into accountinfo(phoneNumber, password, userName, profile, salt, token ) values (#{phoneNumber}, #{password}, #{userName}, #{profile},  #{salt}, #{token})")
    @Options(useGeneratedKeys = true, keyProperty = "ID")
    public int addNewAccount(AccountEntity accountEntity);

    @Select("select * from accountinfo")
    public List<AccountEntity> findAll();

    @Select("select * from accountinfo where token=#{token}")
    public List<AccountEntity> findByToken(String token);

    @Select("select * from accountinfo where phoneNumber=#{phoneNumber}")
    public List<AccountEntity> findByPhone(String phoneNumber);

    @Update("update accountinfo set userName=#{userName} where token=#{token}")
    public int updateUserName(String token, String userName);

    @Update("update accountinfo set phoneNumber=#{phoneNumber} where token=#{token}")
    public int updatePhoneNumber(String token, String phoneNumber);

    @Update("update accountinfo set password=#{password} where token=#{token}")
    public int updatePassword(String token, String password);

    @Update("update accountinfo set profile=#{profile} where token=#{token}")
    public int updateProfile(String token, String profile);
}
