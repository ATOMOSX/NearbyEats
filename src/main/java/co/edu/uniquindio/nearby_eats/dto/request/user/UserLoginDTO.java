package co.edu.uniquindio.nearby_eats.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank(message = "email is required") @Email String email,
        @NotBlank(message = "password is required") String password
) {

}
