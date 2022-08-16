package org.example.chat.room.api.domain.room.req;

import lombok.Data;
import org.example.chat.room.api.domain.AuthReq;

@Data
public class DestroyRoomReq extends AuthReq {
    private Long roomId;
}
