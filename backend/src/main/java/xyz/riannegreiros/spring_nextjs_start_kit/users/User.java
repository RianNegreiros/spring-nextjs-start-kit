package xyz.riannegreiros.spring_nextjs_start_kit.users;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.riannegreiros.spring_nextjs_start_kit.entity.BaseEntity;
import xyz.riannegreiros.spring_nextjs_start_kit.users.data.CreateUserRequest;
import xyz.riannegreiros.spring_nextjs_start_kit.util.ApplicationContextProvider;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private final boolean verified = false;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public User(CreateUserRequest data) {
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        this.email = data.getEmail();
        this.password = passwordEncoder.encode(data.getPassword());
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
        this.role = Role.USER;
    }
}
