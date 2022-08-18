package org.example.chat.room.api.domain.room;

import lombok.Data;

@Data
public class RoomVo {
    private String roomId;
    private String name;
    private int type;
    private Integer maxNum;
    private Integer currNum;
    private Long ownerChatUid;
    private boolean canSearch;
}
