package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.entities.Score;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ItemPlaceStatusDTO(
        @NotBlank(message = "id is required") @Length(max = 100) String id,
        @NotBlank(message = "name is required") String name,
        @NotBlank(message = "description is required") String description
) {
}
