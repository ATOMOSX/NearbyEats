package co.edu.uniquindio.nearby_eats.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank(message = "Token is required") @JsonBackReference String token
) {
}
