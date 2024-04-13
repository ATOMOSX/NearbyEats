package co.edu.uniquindio.nearby_eats.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidationDTO(
        @NotBlank(message = "campo is required") String campo,
        @NotBlank(message = "error is required") String error
) {
}
