package org.example.chat.room.domain.message;


import lombok.Data;

@Data
public class MessageVo {
    private Long sequenceId;
    private Long roomId;
    private Long senderChatUid;
    private String content;
    private Integer type;

    private String senderNickname;
    private String senderAvatar;
}
