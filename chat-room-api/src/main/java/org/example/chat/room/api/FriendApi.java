package org.example.chat.room.api;

import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.chat.room.api.domain.room.RoomVo;
import org.example.chat.room.api.domain.room.req.CreateRoomReq;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "friend")
@RequestMapping("/api/v1/friend")
public interface FriendApi {

    @ApiOperation("add friend")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<RoomVo> addFriend(@Validated @RequestBody CreateRoomReq req);


    @ApiOperation("add friend")
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<RoomVo> deleteFriend(@Validated @RequestBody CreateRoomReq req);


    @ApiOperation("add friend")
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<RoomVo> searchFriend(@Validated @RequestBody CreateRoomReq req);


}
