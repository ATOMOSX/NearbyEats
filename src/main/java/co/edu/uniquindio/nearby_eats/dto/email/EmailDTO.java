package co.edu.uniquindio.nearby_eats.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank(message = "subject is required") @Length(max = 100) String subject,
        @NotBlank(message = "messageBody is required") @Length(max = 288) String messageBody,
        @NotBlank(message = "addressee is required") @Email String addressee) {
}
