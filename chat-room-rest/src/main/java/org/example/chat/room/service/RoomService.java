package org.example.chat.room.service;

import com.tove.web.infra.common.Response;
import org.example.chat.room.api.domain.room.RoomVo;
import org.example.chat.room.api.domain.room.req.CreateRoomReq;
import org.example.chat.room.api.domain.room.req.DestroyRoomReq;
import org.example.chat.room.api.domain.room.req.JoinRoomReq;
import org.example.chat.room.api.domain.room.req.LeaveRoomReq;

public interface RoomService {

     RoomVo  createRoom(CreateRoomReq req);

     Boolean  joinRoom(JoinRoomReq req);

     Boolean  leaveRoom(LeaveRoomReq req);

     Boolean  destroyRoom(DestroyRoomReq req);
}
