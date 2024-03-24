package io.lucky.server.user.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    private String email;
    private String nickname;
    private String password;
}
