package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record ItemPlaceOwnerDTO(
        String name,
        String description,
        String picture,
        List<Schedule> schedule,
        List<Category> categories,
        float score,
        LocalDateTime creationDate
) {
}
