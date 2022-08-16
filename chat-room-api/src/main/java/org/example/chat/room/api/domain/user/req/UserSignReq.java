package org.example.chat.room.api.domain.user.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ApiModel
public class UserSignReq {

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20之间")
    private String password;

    @ApiModelProperty(value = "昵称")
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "用户名")
    private String username;

    private String avatar;
}
