package org.example.chat.common;

public interface RedisConstant {

    /** Set -> roomId: Set<ChatUid>*/
    String ROOM_USER_SET = "chat-room:room:%s";

    /** String -> roomId: DialogVo */
    String ROOM_DIALOG = "chat-room:dialog:%s";

    /** String -> token: UserInfo */
    String TOKEN_USER = "chat-room:ws-token:%s";

    String AUTO_INCREMENT_ID = "chat-room:auto-inc-id:%s";
}

