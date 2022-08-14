package org.example.chat.room.controller;

import com.tove.web.infra.common.Response;
import org.example.chat.room.RoomApi;
import org.example.chat.room.domain.room.RoomVo;
import org.example.chat.room.domain.room.req.CreateRoomReq;
import org.example.chat.room.domain.room.req.DestroyRoomReq;
import org.example.chat.room.domain.room.req.JoinRoomReq;
import org.example.chat.room.domain.room.req.LeaveRoomReq;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController implements RoomApi {

    @Override
    public Response<RoomVo> createRoom(CreateRoomReq req) {
        return null;
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
