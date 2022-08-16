package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.api.ChatUserApi;
import org.example.chat.room.api.domain.user.UserVo;
import org.example.chat.room.api.domain.user.req.UserLoginReq;
import org.example.chat.room.api.domain.user.req.UserSignReq;
import org.example.chat.room.service.ChatUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ChatUserController implements ChatUserApi {

    @Resource
    private ChatUserService chatUserService;

    @Override
    public Response<Boolean> userSign(UserSignReq req) {
        Boolean result = chatUserService.userSign(req);
        return Response.getOk(result);
    }

    @Override
    public Response<UserVo> userLogin(UserLoginReq req) {
        UserVo result = chatUserService.userLogin(req);
        return Response.getOk(result);
    }
}
