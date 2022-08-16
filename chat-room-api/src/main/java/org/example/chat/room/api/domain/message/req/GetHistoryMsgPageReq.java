package org.example.chat.room.api.domain.message.req;

import lombok.Data;

@Data
public class GetHistoryMsgPageReq {
    private Long roomId;
    private Long lastSequenceId;

    private int pageSize = 50;
    private int pageNum = 1;
}
