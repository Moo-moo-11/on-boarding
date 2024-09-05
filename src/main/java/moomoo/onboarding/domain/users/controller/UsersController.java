package moomoo.onboarding.domain.users.controller;

import moomoo.onboarding.domain.users.dto.LoginRequest;
import moomoo.onboarding.domain.users.dto.LoginResponse;
import moomoo.onboarding.domain.users.dto.SignUpRequest;
import moomoo.onboarding.domain.users.dto.UserDto;
import moomoo.onboarding.domain.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/sign-up")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpRequest signUprequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.signUp(signUprequest));
    }

    @PostMapping("/sign-in")
    ResponseEntity<LoginResponse> signIn(@RequestBody LoginRequest loginRequest)  {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usersService.signIn(loginRequest));
    }

}
