package org.example.chat.common.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tove.web.infra.common.BaseErrorCode;
import com.tove.web.infra.common.BaseException;
import lombok.Data;


@Data
public class WsArgBase<T> {
    private WsMsgType type;
    private Long uid;
    private T data;

    public static WsArgBase getMsgType(String msg){ ;
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String type = (String) jsonObject.get("type");
        WsMsgType wsMsgType = WsMsgType.of(type);
        if(wsMsgType == null) {
            throw new BaseException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
        String data = jsonObject.get("data").toString();
        switch (wsMsgType){
            case CHAT_MESSAGE:
                WsArgBase<WsChatMessage> arg = new WsArgBase();
                arg.setType(wsMsgType);
                arg.setData(JSON.parseObject(data, WsChatMessage.class));
                return arg;
            default:
                throw new BaseException(BaseErrorCode.ILLEGAL_PARAMETERS);
        }
    }
}
