package org.example.chat.common.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class Message extends BaseModel {
    private Long sequenceId;
    private String roomId;
    private Long senderChatUid;
    private String content;
    private Integer type;
}
