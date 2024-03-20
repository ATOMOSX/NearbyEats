package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ClientLoginDTO(
        @NotBlank(message = "Nickname is required") String nickname,
        @NotBlank(message = "Password is required") String password
) {
}
