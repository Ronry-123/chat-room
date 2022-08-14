package org.example.chat.room.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthReq {
    @ApiModelProperty(hidden = true)
    private Long chatUid;
}
