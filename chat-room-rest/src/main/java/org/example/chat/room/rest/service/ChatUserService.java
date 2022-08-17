package org.example.chat.room.rest.service;

import org.example.chat.room.api.domain.user.UserVo;
import org.example.chat.room.api.domain.user.req.UserLoginReq;
import org.example.chat.room.api.domain.user.req.UserSignReq;

public interface ChatUserService {

    Boolean userSign(UserSignReq req);
    UserVo userLogin(UserLoginReq req);

}
