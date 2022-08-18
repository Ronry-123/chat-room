package org.example.chat.room.api;

import com.tove.web.infra.common.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomErrorCode implements BaseError {
    NOT_GROUP_OWNER("10001", "不是群主"),
    NOT_EXIST_ROOM("10002", "不存在该房间"),
    EXIST_ROOM("10003", "已存在该房间"),
    ROOM_FULL("10004", "房间已满"),
    EXIST_ROOM_MEMBER("10005", "已存在该成员"),
    ;

   private String code;
   private String msg;
}
