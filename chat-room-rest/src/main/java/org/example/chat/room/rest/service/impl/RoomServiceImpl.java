package org.example.chat.room.rest.service.impl;

import com.tove.web.infra.common.BaseException;
import org.example.chat.common.model.Room;
import org.example.chat.room.api.RoomErrorCode;
import org.example.chat.room.api.domain.room.RoomVo;
import org.example.chat.room.api.domain.room.req.CreateRoomReq;
import org.example.chat.room.api.domain.room.req.DestroyRoomReq;
import org.example.chat.room.api.domain.room.req.JoinRoomReq;
import org.example.chat.room.api.domain.room.req.LeaveRoomReq;
import org.example.chat.room.dao.RoomMapper;
import org.example.chat.room.rest.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public RoomVo createRoom(CreateRoomReq req) {
        // 往群里面存入一个用户
        return null;
    }

    @Override
    public Boolean joinRoom(JoinRoomReq req) {
        return null;
    }

    @Override
    public Boolean leaveRoom(LeaveRoomReq req) {
        return null;
    }

    @Transactional
    @Override
    public Boolean destroyRoom(DestroyRoomReq req) {
        // check 是否群主
        Room room = roomMapper.getRoom(req.getRoomId());
        if (room == null) {
            throw new BaseException(RoomErrorCode.NOT_EXIST_ROOM);
        }
        if (room.getOwnerChatUid() != req.getChatUid()) {
            throw new BaseException(RoomErrorCode.NOT_GROUP_OWNER);
        }
        // remove group
        roomMapper.deleteRoom(req.getRoomId());
        // remove group member
        roomMapper.deleteRoomMember(req.getRoomId());
        return true;
    }
}
