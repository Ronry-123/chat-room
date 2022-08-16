package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.api.RoomApi;
import org.example.chat.room.api.domain.room.RoomVo;
import org.example.chat.room.api.domain.room.req.CreateRoomReq;
import org.example.chat.room.api.domain.room.req.DestroyRoomReq;
import org.example.chat.room.api.domain.room.req.JoinRoomReq;
import org.example.chat.room.api.domain.room.req.LeaveRoomReq;
import org.example.chat.room.service.RoomService;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RoomController implements RoomApi {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RoomService roomService;

    @Override
    public Response<RoomVo> createRoom(CreateRoomReq req) {
        RoomVo result = roomService.createRoom(req);
        return Response.getOk(result);
    }

    @Override
    public Response<Boolean> joinRoom(JoinRoomReq req) {
        return null;
    }

    @Override
    public Response<Boolean> leaveRoom(LeaveRoomReq req) {
        return null;
    }

    @Override
    public Response<Boolean> destroyRoom(DestroyRoomReq req) {
        return null;
    }

}
