package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.MessageApi;
import org.example.chat.room.domain.message.req.GetHistoryMsgPageReq;
import org.example.chat.room.domain.message.req.GetUserDialogReq;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController implements MessageApi {

    @Override
    public Response<Boolean> getHistoryMessagePage(GetHistoryMsgPageReq req) {
        return null;
    }

    @Override
    public Response<Boolean> getUserDialog(GetUserDialogReq req) {
        return null;
    }
}
