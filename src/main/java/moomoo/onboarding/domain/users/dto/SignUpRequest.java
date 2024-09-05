package moomoo.onboarding.domain.users.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {
    private String username;
    private String password;
    private String nickname;
}
