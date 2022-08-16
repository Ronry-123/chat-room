package org.example.chat.room.api;

import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.chat.room.api.domain.user.UserVo;
import org.example.chat.room.api.domain.user.req.UserLoginReq;
import org.example.chat.room.api.domain.user.req.UserSignReq;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "User")
@RequestMapping("/api/v1/chat-user")
public interface ChatUserApi {

    @ApiOperation("用户注册")
    @PostMapping(value = "/sign", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> userSign(@Validated @RequestBody UserSignReq req);

    @ApiOperation("用户登录")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<UserVo> userLogin(@Validated @RequestBody UserLoginReq req);

}
