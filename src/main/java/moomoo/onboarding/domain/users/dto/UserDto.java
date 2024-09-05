package moomoo.onboarding.domain.users.dto;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String nickname;
    private List<AuthorityDto> authorities;
}