package co.edu.uniquindio.nearby_eats.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageDTO <T>(
        @NotBlank(message = "error is required") boolean error,
        @NotBlank(message = "response is required") T response
) {
}
