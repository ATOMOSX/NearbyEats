package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ChangePasswordDTO(
        @NotBlank(message = "Password is required") @Length(max = 100) String password,
        @NotBlank(message = "Token is required") String token,
        String idClient
) {
}
