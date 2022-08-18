package org.example.chat.room.rest.service.impl;

import com.tove.web.infra.common.BaseException;
import com.tove.web.infra.common.tool.RandomUtil;
import org.example.chat.common.model.Room;
import org.example.chat.common.model.RoomMember;
import org.example.chat.common.model.RoomType;
import org.example.chat.room.api.RoomErrorCode;
import org.example.chat.room.api.domain.room.RoomVo;
import org.example.chat.room.api.domain.room.req.CreateRoomReq;
import org.example.chat.room.api.domain.room.req.DestroyRoomReq;
import org.example.chat.room.api.domain.room.req.JoinRoomReq;
import org.example.chat.room.api.domain.room.req.LeaveRoomReq;
import org.example.chat.room.dao.RoomMapper;
import org.example.chat.room.rest.service.CoreRedisService;
import org.example.chat.room.rest.service.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private CoreRedisService coreRedisService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RoomVo createRoom(CreateRoomReq req) {
        // 往群里面存入一个用户
        Room room = new Room();
        Long roomId = RandomUtil.getRandomLong(10);
        room.setRoomId(roomId.toString());
        room.setName(req.getRoomName());
        room.setType(RoomType.of(req.getRoomType()).getCode());
        room.setCanSearch(req.getCanSearch());
        room.setMaxNum(500);
        room.setCurrNum(1);
        room.setOwnerChatUid(req.getChatUid());

        // TODO check member is exist
        Set<Long> memberIds = req.getInvitedUserIds();
        memberIds.add(req.getChatUid());
        try {
            int rows = roomMapper.insertRoom(room);
            Set<RoomMember> members = memberIds.stream().map(memberId -> {
                RoomMember member = new RoomMember();
                member.setRoomId(roomId.toString());
                member.setChatUid(memberId);
                if (memberId.equals(req.getChatUid())) {
                    member.setAdmin(true);
                } else {
                    member.setAdmin(false);
                }
                return member;
            }).collect(Collectors.toSet());
            int rows2 = roomMapper.batchInsertRoomMember(members);
        } catch (Exception e) {
            throw new BaseException(RoomErrorCode.EXIST_ROOM);
        }
        coreRedisService.setRoomMember(roomId.toString(), memberIds);
        RoomVo result = new RoomVo();
        BeanUtils.copyProperties(room, result);
        return result;
    }

    @Override
    public Boolean joinRoom(JoinRoomReq req) {
        Room room = roomMapper.getRoom(req.getRoomId());
        if (Objects.isNull(room)) {
            throw new BaseException(RoomErrorCode.NOT_EXIST_ROOM);
        }
        if(!room.isCanSearch()){
            throw new BaseException(RoomErrorCode.NOT_EXIST_ROOM);
        }
        if (room.getType() == RoomType.FRIEND.getCode()) {
            throw new BaseException(RoomErrorCode.NOT_EXIST_ROOM);
        }
        if (room.getCurrNum() >= room.getMaxNum()) {
            throw new BaseException(RoomErrorCode.ROOM_FULL);
        }
        RoomMember member = new RoomMember();
        member.setRoomId(req.getRoomId());
        member.setChatUid(req.getChatUid());
        member.setAdmin(false);
        try{
            int rows = roomMapper.insertRoomMember(member);
            coreRedisService.addRoomMember(req.getRoomId(), req.getChatUid());
            return rows > 0;
        }catch (DuplicateKeyException e){
            throw new BaseException(RoomErrorCode.EXIST_ROOM_MEMBER);
        }
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
        if (!Objects.equals(room.getOwnerChatUid(), req.getChatUid())) {
            throw new BaseException(RoomErrorCode.NOT_GROUP_OWNER);
        }
        // remove group
        roomMapper.deleteRoom(req.getRoomId());
        // remove group member
        roomMapper.deleteRoomAllMember(req.getRoomId());
        coreRedisService.removeAllRoomMember(req.getRoomId());
        return true;
    }

    /**
     * 生成私聊房间id
     * @param toChatUid
     * @param fromChatUid
     * @return
     */
    private String generateFriendRoomId(Long toChatUid, Long fromChatUid) {
        if (toChatUid < fromChatUid) {
            return String.format("%d%d", toChatUid, fromChatUid);
        } else {
            return String.format("%d%d", fromChatUid, toChatUid);
        }
    }
}
