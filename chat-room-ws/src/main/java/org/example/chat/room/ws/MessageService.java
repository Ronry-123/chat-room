package org.example.chat.room.ws;

import com.tove.web.infra.common.BaseErrorCode;
import com.tove.web.infra.common.BaseException;
import com.tove.web.infra.common.WsResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.common.model.RoomType;
import org.example.chat.common.ws.WsArgBase;
import org.example.chat.common.ws.WsChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import static org.example.chat.common.ws.WsMsgType.CHAT_MESSAGE;


@Slf4j
@Service
public class MessageService {


    @Resource
    private RedisService redisService;

    public void msgHandler(WsArgBase arg) {
        switch (arg.getType()){
            case CHAT_MESSAGE:
                WsChatMessage msg = (WsChatMessage) arg.getData();
                System.out.println(msg.getContent());
                wsChatMessageHandler(msg);
                break;
            default:
                throw new BaseException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
    }

    private void wsChatMessageHandler(WsChatMessage msg) {
        RoomType roomType = RoomType.of(msg.getRoomType());
        Long seq = redisService.getAutoIncrementId(msg.getRoomId());
        msg.setSequenceId(seq);
        List<Long> uids = null;
        switch (roomType){
            case FRIEND:
                uids = redisService.getRoomUidList(msg.getRoomId());
                if (uids == null || uids.size() == 0){
                    log.info("no user in room");
                }
                if (uids.size() == 1){
                    log.info("only one user in room");
                    if (uids.get(0).equals(msg.getSenderChatUid())){
                       log.error("send message to self");
                    }
                }
                if (uids.size() == 2){
                    log.info("two user in room");
                    for(Long uid: uids){
                        if (uid.equals(msg.getSenderChatUid())){
                            continue;
                        }
                        WsResponse wsResponse = WsResponse.getOk(msg, CHAT_MESSAGE.name());
                        WebSocketUtils.sendMessage(uid, wsResponse);
                    }
                }
                break;
            case GROUP:
                uids = redisService.getRoomUidList(msg.getRoomId());
                for(Long uid: uids){
                    WsResponse wsResponse = WsResponse.getOk(msg, CHAT_MESSAGE.name());
                    WebSocketUtils.sendMessage(uid, wsResponse);
                }
                break;
            case CHANNEL:
                break;
            default:
                throw new BaseException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
    }
}
