package co.edu.uniquindio.nearby_eats.dto.image;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ImageDTO(
        @NotBlank(message = "messageBody is required") @Length(max = 100) String idImage,
        @NotBlank(message = "messageBody is required")  String url
) {
}
