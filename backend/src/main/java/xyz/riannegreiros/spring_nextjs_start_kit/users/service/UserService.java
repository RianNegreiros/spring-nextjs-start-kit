package xyz.riannegreiros.spring_nextjs_start_kit.users.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.riannegreiros.spring_nextjs_start_kit.users.User;
import xyz.riannegreiros.spring_nextjs_start_kit.users.data.CreateUserRequest;
import xyz.riannegreiros.spring_nextjs_start_kit.users.data.UserResponse;
import xyz.riannegreiros.spring_nextjs_start_kit.users.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    @Transactional
    public UserResponse create(@Valid CreateUserRequest request) {
        User user = new User(request);
        user = userRepository.save(user);
        return new UserResponse(user);
    }
}
