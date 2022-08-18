package org.example.chat.room.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.example.chat.common.model.User;
@Mapper
public interface UserMapper {

    @Select("select 1 from user where email = #{email}")
    Integer existUserByEmail(String email);

    @Insert("INSERT INTO user (chat_uid, username, email, password, nickname, avatar) " +
            "VALUES (#{chatUid}, #{username}, #{email}, #{password}, #{nickname}, #{avatar})")
    int insert(User user);

    @Select("select id, chat_uid, username, email, password, nickname, avatar FROM user WHERE email = #{email}")
    @Result(column="chat_uid", property="chatUid")
    User selectByEmail(String email);
}
