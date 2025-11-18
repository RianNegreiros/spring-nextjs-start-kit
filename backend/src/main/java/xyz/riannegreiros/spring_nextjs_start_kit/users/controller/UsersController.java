package xyz.riannegreiros.spring_nextjs_start_kit.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.riannegreiros.spring_nextjs_start_kit.users.User;
import xyz.riannegreiros.spring_nextjs_start_kit.users.data.CreateUserRequest;
import xyz.riannegreiros.spring_nextjs_start_kit.users.data.UserResponse;
import xyz.riannegreiros.spring_nextjs_start_kit.users.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        UserResponse user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}
