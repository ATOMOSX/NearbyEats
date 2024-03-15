package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;

public record SearchPlaceDTO(

        @NotBlank(message = "El campo name es required" ) String name,
        @NotBlank(message = "El campo category is required" ) String category,
        @NotBlank(message = "El campo location es required" ) String location
        
) {
}
