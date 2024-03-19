package co.edu.uniquindio.proyecto.dto.revision;

import jakarta.validation.constraints.NotBlank;

public record DeclinePlaceDTO(
        String idPlace,
        @NotBlank(message = "idModerator is required" )String idModerator,
        @NotBlank(message = "Describe el motivo por el cual fue rechazado el lugar" ) String description

) {
}
