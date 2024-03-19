package co.edu.uniquindio.proyecto.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemClientDTO(
        @NotBlank(message = "Id is required") @Length(max = 100) String id,
        @NotBlank(message = "First name is required") @Length(max = 100) String firstName,
        @NotBlank(message = "Last name is required") @Length(max = 100) String lastName,
        @NotBlank(message = "Profile photo is required") String profilePhoto,
        @NotBlank(message = "Nick is required") @Length(max = 100 ) String nickname,
        @NotBlank(message = "Email is required") @Email String email) {
}
