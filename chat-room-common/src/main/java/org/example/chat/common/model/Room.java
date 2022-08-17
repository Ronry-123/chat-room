package org.example.chat.common.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class Room extends BaseModel {
    private String roomId;
    /**
     * 房间名称(friend， group， channel)
     */
    private String name;

    /**
     * 房间类型
     */
    private int type;

    /**
     * 房间最大人数
     */
    private Integer maxNum;

    /**
     * 房间当前人数
     */
    private Integer currNum;

    /**
     * 房间创建者uid
     */
    private Long ownerChatUid;

    /**
     * 是否公开， 默认公开
     */
    private boolean canSearch;

    /**
     * 删除 room id
     */
    private Long deleteRoomId;
}
