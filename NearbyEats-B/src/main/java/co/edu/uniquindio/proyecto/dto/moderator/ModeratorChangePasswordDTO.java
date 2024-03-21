package co.edu.uniquindio.proyecto.dto.moderator;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ModeratorChangePasswordDTO(
        @NotBlank(message = "Password is required") @Length(max = 100) String password,
        @NotBlank(message = "Token is required") String token,
        @NotBlank(message = "id is required") @Length(max = 100) String id
) {
}
