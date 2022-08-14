package org.example.chat.room;

import com.tove.web.infra.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.statement.select.Join;
import org.example.chat.room.domain.room.RoomVo;
import org.example.chat.room.domain.room.req.CreateRoomReq;
import org.example.chat.room.domain.room.req.DestroyRoomReq;
import org.example.chat.room.domain.room.req.JoinRoomReq;
import org.example.chat.room.domain.room.req.LeaveRoomReq;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "room")
@RequestMapping("/api/v1/room")
public interface RoomApi {

    @ApiOperation("create room")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<RoomVo> createRoom(@Validated @RequestBody CreateRoomReq req);

    @ApiOperation("join room")
    @PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> joinRoom(@Validated @RequestBody JoinRoomReq req);

    @ApiOperation("leave room")
    @PostMapping(value = "/leave", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> leaveRoom(@Validated @RequestBody LeaveRoomReq req);

    @ApiOperation("destroy room")
    @PostMapping(value = "/destroy", produces = MediaType.APPLICATION_JSON_VALUE)
    Response<Boolean> destroyRoom(@Validated @RequestBody DestroyRoomReq req);

}
