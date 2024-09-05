package moomoo.onboarding.domain.users.service;

import moomoo.onboarding.domain.users.dto.*;
import moomoo.onboarding.domain.users.model.UserRole;
import moomoo.onboarding.domain.users.model.Users;
import moomoo.onboarding.domain.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto signUp(SignUpRequest signUpRequest) {

        boolean isExistUsername = usersRepository.existsByUsername(signUpRequest.getUsername());

        if(isExistUsername) throw new IllegalArgumentException();

        String password = passwordEncoder.encode(signUpRequest.getPassword());

        Users user = Users.toEntity(signUpRequest.getUsername(), password, signUpRequest.getNickname(), UserRole.USER);

        usersRepository.save(user);

        AuthorityDto authority = new AuthorityDto("ROLE_" + user.getRole().name());

        return new UserDto(user.getUsername(), user.getNickname(), List.of(authority));
    }

    public LoginResponse signIn(LoginRequest loginRequest) {
        return new LoginResponse("gggggggg");
    }
}
