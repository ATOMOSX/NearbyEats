package co.edu.uniquindio.proyecto.dto.moderator;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ModeratorLoginDTO(
        @NotBlank(message = "Nickname is required") @Length(max = 100) String nickname,
        @NotBlank(message = "Password is required") @Length(max = 100) String password) {

}
