package org.example.chat.room.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.chat.common.model.Room;

@Mapper
public interface RoomMapper {

    @Select("select * from room where room_id = #{roomId}")
    Room getRoom(Long roomId);

    @Delete("delete from room where room_id = #{roomId}")
    int deleteRoom(Long roomId);

    @Delete("delete from room_member where room_id = #{roomId}")
    int deleteRoomMember(Long roomId);
}
