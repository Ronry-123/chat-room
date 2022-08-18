package org.example.chat.room.rest.service.impl;

import com.tove.web.infra.common.BaseException;
import com.tove.web.infra.common.tool.Md5Tools;
import com.tove.web.infra.common.tool.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.chat.common.RoomErrorCode;
import org.example.chat.common.model.User;
import org.example.chat.common.ws.UserInfo;
import org.example.chat.room.dao.UserMapper;
import org.example.chat.room.api.domain.user.UserVo;
import org.example.chat.room.api.domain.user.req.UserLoginReq;
import org.example.chat.room.api.domain.user.req.UserSignReq;
import org.example.chat.room.rest.service.ChatUserService;
import org.example.chat.room.rest.service.CoreRedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class ChatUserServiceImpl implements ChatUserService {

    private static final int CHAT_UID_LEN = 10;
    @Resource
    private UserMapper userMapper;

    @Resource
    private CoreRedisService redisService;

    @Override
    public Boolean userSign(UserSignReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        // 密码加密
        user.setPassword(Md5Tools.MD5(user.getPassword()));
        if (userMapper.existUserByEmail(user.getEmail()) != null) {
            throw new BaseException(RoomErrorCode.USER_EXIST);
        }
        int maxTryTime = 3;
        while (maxTryTime > 0) {
            try {
                user.setChatUid(RandomUtil.getRandomLong(10));
                int rows = userMapper.insert(user);
                return rows > 0;
            }catch (DuplicateKeyException e){
                maxTryTime--;
            }
        }
        return false;
    }

    @Override
    public UserVo userLogin(UserLoginReq req) {
        User user = userMapper.selectByEmail(req.getEmail());
        if (user == null){
            throw new BaseException(RoomErrorCode.USER_NOT_EXIST);
        }
        log.info("userLogin user:{}", user);

        String password = Md5Tools.MD5(req.getPassword());
        if (!password.equals(user.getPassword())){
            throw new BaseException(RoomErrorCode.PASSWORD_ERROR);
        }
        UserVo result = new UserVo();
        result.setChatUid(user.getChatUid());
        result.setAvatar(user.getAvatar());
        result.setEmail(user.getEmail());
        result.setWsToken(getWsToken(user));
        return result;
    }

    private String getWsToken(User user){
        Long uid = user.getChatUid();
        String token = Md5Tools.MD5(String.format("%s%s", uid, System.currentTimeMillis()));
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);
        redisService.setUserInfo(token, userInfo);
        return token;
    }
}
