package com.psm.mapper;

import com.psm.entity.AccountEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {
    @Insert("insert into accountinfo(phoneNumber, password, userName, profile, salt, token ) values (#{phoneNumber}, #{password}, #{userName}, #{profile},  #{salt}, #{token})")
    public int addNewAccount(String phoneNumber,String password, String userName, String profile, String salt, String token);
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

}
