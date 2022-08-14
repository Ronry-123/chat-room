package org.example.chat.room.domain.user;

import lombok.Data;

@Data
public class UserVo {
    private String wsToken;
    private Long chatUid;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String avatar;
}
