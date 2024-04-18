package co.edu.uniquindio.nearby_eats.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserChangePasswordDTO(
        @NotBlank(message = "newPassword is required for to change password") String newPassword,
        @NotBlank(message = "recoveryToken is requires for to change password") String recoveryToken
) {
}
