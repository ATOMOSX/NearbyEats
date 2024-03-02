package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateAccountDTO(String id,
                               @NotBlank(message = "Name is required") @Length(max = 100) String name,
                               @Email String email,
        String profilePhoto) {
}