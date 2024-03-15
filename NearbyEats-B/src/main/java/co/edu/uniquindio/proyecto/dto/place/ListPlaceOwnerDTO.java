package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ListPlaceOwnerDTO(
        @NotBlank(message = "id is required" ) String idClient,
        @NotBlank(message = "List the places is required" )List<String> places,
        @NotBlank(message = "Status is required" ) String status
        ) {
}
