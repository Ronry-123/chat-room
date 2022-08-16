package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.api.MessageApi;
import org.example.chat.room.api.domain.message.DialogVo;
import org.example.chat.room.api.domain.message.MessageVo;
import org.example.chat.room.api.domain.message.req.GetHistoryMsgPageReq;
import org.example.chat.room.api.domain.message.req.GetUserDialogReq;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController implements MessageApi {

    @Override
    public Response<List<MessageVo>> getHistoryMessagePage(GetHistoryMsgPageReq req) {
        return null;
    }

    @Override
    public Response<List<DialogVo>> getUserDialog(GetUserDialogReq req) {
        return null;
    }
}
