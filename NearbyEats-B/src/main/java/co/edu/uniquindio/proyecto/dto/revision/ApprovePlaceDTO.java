package co.edu.uniquindio.proyecto.dto.revision;

import jakarta.validation.constraints.NotBlank;

public record ApprovePlaceDTO(
        @NotBlank(message = "idClient is required" )String idClient,
        @NotBlank(message = "idPlaceRequest is required" ) String idPlaceRequest
        ) {
}
