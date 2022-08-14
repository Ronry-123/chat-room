package org.example.chat.common.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WsMsgType {
    CHAT_MESSAGE,
    ;

    public static WsMsgType of(String name) {
        if(name == null || name.length() == 0){
            return null;
        }
        for (WsMsgType type : WsMsgType.values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
