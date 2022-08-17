package org.example.chat.room.ws;

import com.alibaba.fastjson.JSON;
import com.tove.web.infra.common.WsResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.common.ws.UserInfo;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketUtils {
    static class SessionManager{
        /** 用户同时连接数 */
        private static final int USER_CONNECTION_LIMIT = 100;
        private static final int EXPIRE_TIME = 10 * 60 * 1000;
        private static final int MAX_CONNECT_TIME = 24 * 60 * 60 * 1000;
        /** key is user id */
        private static final Map<Long, List<Session>> ONLINE_USER_SESSIONS_MAP = new ConcurrentHashMap<>(1024);
        /** key is user id */
        private static final Map<Long, UserInfo> USER_MAP = new ConcurrentHashMap<>(1024);
        /** key is session id */
        private static final Map<String, UserConnect> SESSION_ID_USER_MAP = new ConcurrentHashMap<>(1024);

        public static void addSession(Session session, UserInfo userInfo){
            Long uid = userInfo.getChatUid();
            String sessionId = session.getId();

            List<Session> userSessions = ONLINE_USER_SESSIONS_MAP.getOrDefault(uid, new ArrayList<>());
            if (userSessions.size() >= USER_CONNECTION_LIMIT){
                log.warn("user {} has too many connection", uid);
                return;
            }
            userSessions.add(session);
            ONLINE_USER_SESSIONS_MAP.put(uid, userSessions);
            USER_MAP.put(uid, userInfo);
            SESSION_ID_USER_MAP.put(sessionId, new UserConnect(uid));
        }

        public static void removeSession(Session session){
            String sessionId = session.getId();
            UserConnect userConnect = SESSION_ID_USER_MAP.get(sessionId);
            Long uid = userConnect.getUid();
            if (uid == null){
                return;
            }
            List<Session> userSessions = ONLINE_USER_SESSIONS_MAP.get(uid);
            if (userSessions == null){
                return;
            }
            userSessions.remove(session);
            if (userSessions.isEmpty()){
                ONLINE_USER_SESSIONS_MAP.remove(uid);
                USER_MAP.remove(uid);
                SESSION_ID_USER_MAP.remove(sessionId);
            }
        }

        public static void removeExpireConnect(){
            for(Map.Entry<String, UserConnect> entry: SESSION_ID_USER_MAP.entrySet()){
                long activeDiff = System.currentTimeMillis() - entry.getValue().getLastActiveTime().getTime();
                long maxDiff = System.currentTimeMillis() - entry.getValue().getConnectTime().getTime();
                if (activeDiff < EXPIRE_TIME && maxDiff < MAX_CONNECT_TIME){
                    continue;
                }
                SESSION_ID_USER_MAP.remove(entry.getKey());
                Long uid = entry.getValue().getUid();
                if (uid == null){
                    continue;
                }
                List<Session> userSessions = ONLINE_USER_SESSIONS_MAP.get(uid);
                if (userSessions == null){
                    continue;
                }
                for(Session session: userSessions){
                    if (session.getId().equals(entry.getKey())){
                        userSessions.remove(session);
                        break;
                    }
                }
                if (userSessions.isEmpty()){
                    ONLINE_USER_SESSIONS_MAP.remove(uid);
                    USER_MAP.remove(uid);
                }
            }
        }

        public static List<Session> getSession(Long uid){
            return ONLINE_USER_SESSIONS_MAP.get(uid);
        }

        public static int getOnlineUserNum(){
            return USER_MAP.size();
        }

        public static int getConnectNum(){
            return SESSION_ID_USER_MAP.size();
        }

        public static UserConnect getUserInfo(String sessionId){
            return SESSION_ID_USER_MAP.get(sessionId);
        }
    }

    @Data
    static class UserConnect{
        private Long uid;
        private Date lastActiveTime = new Date();
        private Date connectTime = new Date();

        public UserConnect(Long uid){
            this.uid = uid;
        }
    }

    /**
     * @param session 当前WebSocket连接的Session实例
     * @param message 消息内容
     */
    public static void sendMessage(Session session, String message) {
        if (session == null || !session.isOpen()) {
            log.warn("session is null or closed");
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            log.warn("basic is null");
            return;
        }
        try {
            basic.sendText(message);
            updateLastActiveTime(session.getId());
        } catch (IOException e) {
            log.error("sendMessage IOException ", e);
        }
    }

    public static void sendMessage(Session session, WsResponse response) {
        String message = JSON.toJSONString(response);
        sendMessage(session, message);
    }

    public static void sendMessage(Long uid, WsResponse response) {
        SessionManager.getSession(uid).forEach(session -> sendMessage(session, response));
    }

    public static void pushPong(Session session) {
        try {
            session.getBasicRemote().sendText("pong");
            updateLastActiveTime(session.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLastActiveTime(String sessionId) {
        SessionManager.SESSION_ID_USER_MAP.get(sessionId).setLastActiveTime(new Date());
    }
}
