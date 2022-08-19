package org.example.chat.room.ws;

import com.tove.web.infra.common.BaseErrorCode;
import com.tove.web.infra.common.BaseException;
import com.tove.web.infra.common.WsResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.common.ws.UserInfo;
import org.example.chat.common.ws.WsArgBase;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@ServerEndpoint(value = "/chat-room/{token}")
@Component
public class ChatRoomRouter {


    private static MessageService messageService;


    private static RedisService redisService;

    @Resource
    public void setRedisService(RedisService redisService) {
        ChatRoomRouter.redisService = redisService;
    }

    @Resource
    private void setMessageService(MessageService messageService) {
        ChatRoomRouter.messageService = messageService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        if (!StringUtils.hasLength(token)){
            log.error("token not null");
        }
        UserInfo userInfo = redisService.getUserInfo(token);
        if (userInfo == null){
            log.error("token not null");
        }
        userInfo.setChatUid(userInfo.getChatUid());
        WebSocketUtils.SessionManager.addSession(session, userInfo);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        WebSocketUtils.SessionManager.removeSession(session);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (!StringUtils.hasLength(message)) {
            return;
        }
        if("ping".equals(message.toLowerCase())) {
            WebSocketUtils.sendMessage(session, "pong");
            // 记录连接最后活跃的时间
            return;
        }
        System.out.println("receive message: " + message);
        try {
            WsArgBase arg = WsArgBase.getMsgType(message);
            arg.setUid(WebSocketUtils.SessionManager.getUserInfo(session.getId()).getUid());
            messageService.msgHandler(arg);
        }catch (BaseException e){
            log.warn("error: {}", e.getMsg());
            WebSocketUtils.sendMessage(session, WsResponse.getFail(e));
        }catch (Exception e){
            log.warn("error: {}", e.getMessage());
            WebSocketUtils.sendMessage(session, WsResponse.getFail(BaseErrorCode.SYSTEM_BUSY));
        }
    }

    /**
     * Throwable error: 不能改变异常的类型，否则会导致服务器无法捕获异常
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("onError session: {}, error: {}", session.getId(), error.getMessage());
        if (error instanceof BaseException){
            BaseException baseException = (BaseException) error;
            WebSocketUtils.sendMessage(session, WsResponse.getFail(baseException));
        } else {
            WebSocketUtils.sendMessage(session, WsResponse.getFail(BaseErrorCode.SYSTEM_BUSY));
        }
    }
}
