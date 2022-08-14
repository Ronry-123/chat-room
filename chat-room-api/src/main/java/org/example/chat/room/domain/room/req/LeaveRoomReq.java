package org.example.chat.room.domain.room.req;

import lombok.Data;
import org.example.chat.room.domain.AuthReq;

@Data
public class LeaveRoomReq extends AuthReq {
    private Long roomId;
}
