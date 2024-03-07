package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;

public record ClientRegistrationDTO(
        @NotBlank(message = "First name is required") @Length(max = 100) String firstName,
        @NotBlank(message = "Last name is required") @Length(max = 100) String lastName,
        @NotBlank(message = "Nickname is required") @Length(max = 100) String nickname,
        @NotBlank(message = "Email is required") @Email String email,
        @NotBlank(message = "Profile photo is required") String profilePhoto,
        @NotBlank(message = "City is required") @Length(max = 100) String city,
        @NotBlank(message = "Password is required") @Length(max = 100) String password) {
}
