package org.example.chat.room.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.chat.common.model.Room;
import org.example.chat.common.model.RoomMember;

import java.util.Set;

@Mapper
public interface RoomMapper {

    @Select("select * from room where room_id = #{roomId}")
    Room getRoom(String roomId);

    @Delete("delete from room where room_id = #{roomId}")
    int deleteRoom(String roomId);

    @Delete("delete from room_member where room_id = #{roomId}")
    int deleteRoomAllMember(String roomId);

    @Insert("insert into room(room_id, name, type, max_num, curr_num, owner_chat_uid, can_search) " +
            "values(#{roomId}, #{name}, #{type}, #{maxNum}, #{currNum}, #{ownerChatUid}, #{canSearch})")
    int insertRoom(Room room);

    @Insert("<script>" +
            "insert into room_member(room_id, chat_uid, admin) values " +
            "<foreach collection='roomMembers' item='roomMember' separator=','>" +
            "(#{roomMember.roomId}, #{roomMember.chatUid}, #{roomMember.admin})" +
            "</foreach>" +
            "</script>")
    int batchInsertRoomMember(Set<RoomMember> roomMembers);

    @Insert("insert into room_member(room_id, chat_uid, admin) values(#{roomId}, #{chatUid}, #{admin})")
    int insertRoomMember(RoomMember member);
}
