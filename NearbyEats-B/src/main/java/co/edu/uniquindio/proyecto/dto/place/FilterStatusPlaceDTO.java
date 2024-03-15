package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;

public record FilterStatusPlaceDTO(
        @NotBlank(message = "El campo idPlace es required" ) String idPlace,
        @NotBlank(message = "El campo status es required" ) String status
) {
}
