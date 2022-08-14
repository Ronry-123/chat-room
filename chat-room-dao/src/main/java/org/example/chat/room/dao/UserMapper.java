package org.example.chat.room.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.chat.common.model.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (user_id, user_name, user_pwd, user_email, user_phone, user_status, user_create_time, user_update_time) " +
            "VALUES (#{userId}, #{userName}, #{userPwd}, #{userEmail}, #{userPhone}, #{userStatus}, #{userCreateTime}, #{userUpdateTime})")
    int insert(User user);

    @Select("SELECT * FROM user WHERE user_email = #{userEmail}")
    User selectByEmail(String email);
}
