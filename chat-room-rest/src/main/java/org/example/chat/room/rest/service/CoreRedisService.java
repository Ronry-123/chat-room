package org.example.chat.room.rest.service;

import org.example.chat.common.ws.UserInfo;

import java.util.Set;

public interface CoreRedisService {
    void setUserInfo(String token, UserInfo userInfo);

    void setRoomMember(String roomId, Set<Long> uids);

    void addRoomMember(String roomId, Long uid);

    void removeRoomMember(String roomId, Long uid);

    void removeAllRoomMember(String roomId);
}
