package xyz.riannegreiros.spring_nextjs_start_kit.users.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import xyz.riannegreiros.spring_nextjs_start_kit.util.validators.PasswordMatch;
import xyz.riannegreiros.spring_nextjs_start_kit.util.validators.Unique;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "passwordConfirmation")
public class CreateUserRequest {
    
    @Email
    @Unique(columnName = "email", tableName = "user", message = "User with this email already exists")
    private String email;
    
    @NotNull
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "must contain at least one uppercase letter, one lowercase letter, and one digit.")
    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;
}
