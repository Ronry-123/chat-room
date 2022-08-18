package org.example.chat.room.api.domain.room.req;

import lombok.Data;
import org.example.chat.common.model.RoomType;
import org.example.chat.room.api.domain.AuthReq;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class CreateRoomReq extends AuthReq {

    @NotEmpty
    private String roomName;
    private String roomType = RoomType.GROUP.name();
    private Boolean canSearch = true;
    private Set<Long> invitedUserIds = new HashSet<>();
}
