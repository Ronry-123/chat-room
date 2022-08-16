package org.example.chat.room.api.domain.message;

import lombok.Data;
import java.util.Date;

@Data
public class DialogVo {
    private Long roomId;
    private String roomName;
    private int roomType;

    private Date lastMsgTime;
    private String lastMsg;
    private Long lastMsgType;
}
