package co.edu.uniquindio.nearby_eats.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank(message = "affair is required") @Length(max = 100) String affair,
        @NotBlank(message = "messageBody is required") String messageBody,
        @NotBlank(message = "addressee is required") @Email String addressee) {
}
