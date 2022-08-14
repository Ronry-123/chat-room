package org.example.chat.common.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class RoomMember extends BaseModel {
    private Long roomId;
    private Long chatUid;
    private boolean isAdmin;
}
