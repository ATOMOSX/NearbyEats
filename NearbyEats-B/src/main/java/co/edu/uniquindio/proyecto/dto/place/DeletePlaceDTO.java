package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;

public record DeletePlaceDTO(
        @NotBlank(message = "El campo idPlace es required" ) String idPlace,
        String clientId
) {
}
