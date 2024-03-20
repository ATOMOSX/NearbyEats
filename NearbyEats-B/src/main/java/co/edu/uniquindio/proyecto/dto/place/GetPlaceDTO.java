package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.documents.Revision;
import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;

import java.util.List;

public record GetPlaceDTO(
        String id,
        String name,
        String description,
        Ubication location,
        List<String> pictures,
        List<Schedule> schedule,
        List<String> phones,
        List<Category> categories,
        List<Revision> revisionsHistory
) {
}
