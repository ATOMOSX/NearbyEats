package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemClientDTO(
        @NotBlank(message = "id is required") @Length(max = 100) String id,
        String firstName,
        String LastName,
        String perfilePhoto,
        String nickname,
        String email) {
}
