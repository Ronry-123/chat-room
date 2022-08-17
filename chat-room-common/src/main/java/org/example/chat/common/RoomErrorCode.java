package org.example.chat.common;

import com.tove.web.infra.common.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RoomErrorCode implements BaseError {

    USER_NOT_EXIST("50004", "不存在该用户"),

    PASSWORD_ERROR("50005", "密码错误"),
    USER_EXIST("50006","用户已经存在" );
    private final String code;
    private final String msg;
}
