package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.ChatUserApi;
import org.example.chat.room.domain.user.UserVo;
import org.example.chat.room.domain.user.req.UserLoginReq;
import org.example.chat.room.domain.user.req.UserSignReq;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ChatUserController implements ChatUserApi {

    @Resource


    @Override
    public Response<Boolean> userSign(UserSignReq req) {
        return null;
    }

    @Override
    public Response<UserVo> userLogin(UserLoginReq req) {
        return null;
    }
}
