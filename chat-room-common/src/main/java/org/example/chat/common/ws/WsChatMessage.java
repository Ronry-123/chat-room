package org.example.chat.common.ws;


import lombok.Data;

import java.util.Date;

@Data
public class WsChatMessage {
    private Long sequenceId;

    private String roomId;
    private String roomType;

    private Long senderChatUid;
    private String content;
    private Integer msgType;
    private Date time = new Date();
}
