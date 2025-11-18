package xyz.riannegreiros.spring_nextjs_start_kit.users.data;

import lombok.Data;
import xyz.riannegreiros.spring_nextjs_start_kit.users.Role;
import xyz.riannegreiros.spring_nextjs_start_kit.users.User;

@Data
public class UserResponse {
    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}