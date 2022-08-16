package org.example.chat.room.api;


import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.chat.room.api.domain.message.DialogVo;
import org.example.chat.room.api.domain.message.MessageVo;
import org.example.chat.room.api.domain.message.req.GetHistoryMsgPageReq;
import org.example.chat.room.api.domain.message.req.GetUserDialogReq;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api(value = "message")
@RequestMapping("/api/v1/message")
public interface MessageApi {

    @ApiOperation("get history message page ")
    @PostMapping(value = "/get-history-page", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<List<MessageVo>> getHistoryMessagePage(@Validated @RequestBody GetHistoryMsgPageReq req);

    @ApiOperation("get user dialog")
    @PostMapping(value = "/get-user-dialog", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<List<DialogVo>> getUserDialog(@Validated @RequestBody GetUserDialogReq req);

}
