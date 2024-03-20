package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemClientDTO<City>(
        String id,
        String firstName,
        String lastName,
        String profilePhoto,
        String nickname,
        String email,
        City city
) {
}