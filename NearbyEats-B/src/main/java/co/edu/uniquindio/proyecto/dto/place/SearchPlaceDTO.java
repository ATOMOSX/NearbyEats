package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import jakarta.validation.constraints.NotBlank;

public record SearchPlaceDTO(

        @NotBlank(message = "El campo name es required" ) String name,
        @NotBlank(message = "El campo category is required" ) Category category,
        @NotBlank(message = "El campo location es required" ) Ubication location
        
) {
}
