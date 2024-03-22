package co.edu.uniquindio.proyecto.dto.moderator;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ModeratorLoginDTO(
        @NotBlank(message = "Email is required") @Length(max = 100) String email,
        @NotBlank(message = "Password is required") @Length(max = 100) String password) {

}
