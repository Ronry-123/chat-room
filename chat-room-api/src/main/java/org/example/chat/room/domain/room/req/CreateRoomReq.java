package org.example.chat.room.domain.room.req;

import lombok.Data;
import org.example.chat.room.domain.AuthReq;

import java.util.List;

@Data
public class CreateRoomReq extends AuthReq {
    private String roomName;
    private String roomType;
    private List<Long> invitedUserIds;
}
