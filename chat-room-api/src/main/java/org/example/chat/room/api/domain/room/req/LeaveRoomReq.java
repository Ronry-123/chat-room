package org.example.chat.room.api.domain.room.req;

import lombok.Data;
import org.example.chat.room.api.domain.AuthReq;

import javax.validation.constraints.NotEmpty;

@Data
public class LeaveRoomReq extends AuthReq {
    @NotEmpty
    private String roomId;
}
