package org.example.chat.room.service;

import org.example.chat.room.domain.user.UserVo;
import org.example.chat.room.domain.user.req.UserLoginReq;
import org.example.chat.room.domain.user.req.UserSignReq;

public interface ChatUserService {

    Boolean userSign(UserSignReq req);
    UserVo userLogin(UserLoginReq req);


}
