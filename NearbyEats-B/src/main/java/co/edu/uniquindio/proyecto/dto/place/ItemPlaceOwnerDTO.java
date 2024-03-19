package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemPlaceOwnerDTO(
        @NotBlank(message = "idOwner is required") @Length(max = 100) String idOwner,
        @NotBlank(message = "namePlace is required") String namePlace,
        @NotBlank(message = "description is required") String description
) {
}
