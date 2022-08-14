package org.example.chat.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    TEXT(1),
    IMAGE(2),
    FILE(3),
    VIDEO(4),
    VOICE(5),
    ;

    private final int code;

    public static MessageType of(int code) {
        for (MessageType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
