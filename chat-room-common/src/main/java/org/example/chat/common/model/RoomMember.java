package org.example.chat.common.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class RoomMember extends BaseModel {
    private String roomId;
    private Long chatUid;
    private Boolean admin;
}
