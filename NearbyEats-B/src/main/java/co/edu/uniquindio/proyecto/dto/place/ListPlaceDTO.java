package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;

import java.util.List;

public record ListPlaceDTO(
        String name,
        String description,
        Ubication location,
        String picture,
        List<Schedule> schedule,
        List<Category> categories
) {
}
