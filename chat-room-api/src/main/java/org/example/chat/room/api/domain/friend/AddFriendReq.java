package org.example.chat.room.api.domain.friend;

import lombok.Data;
import org.example.chat.room.api.domain.AuthReq;

@Data
public class AddFriendReq extends AuthReq {
    private Long toChatUid;
}
