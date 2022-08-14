package org.example.chat.common.model;

import com.tove.web.infra.common.BaseModel;
import lombok.Data;

@Data
public class User extends BaseModel {
    private Long chatUid;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String avatar;
    private Long deleteUid;

}
