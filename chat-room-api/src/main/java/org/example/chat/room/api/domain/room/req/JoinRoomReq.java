package org.example.chat.room.api.domain.room.req;

import lombok.Data;
import org.example.chat.room.api.domain.AuthReq;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class JoinRoomReq extends AuthReq {
    @NotEmpty
    private String roomId;
}
