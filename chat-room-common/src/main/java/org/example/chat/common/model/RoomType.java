package org.example.chat.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {
    FRIEND(0),
    GROUP(1),
    CHANNEL(2),
    ;

    private final int code;

    public static RoomType of(String roomType) {
        if(roomType == null || roomType.length() == 0){
            return null;
        }
        for (RoomType type : RoomType.values()) {
            if (type.name().equals(roomType)) {
                return type;
            }
        }
        return null;
    }
}
