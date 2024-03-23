package co.edu.uniquindio.proyecto.dto.place;

import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record CreatePlaceDTO(
        @NotBlank(message = "El campo nombre es requerido" ) String name,
        @NotBlank(message = "El campo descripcion esta vacio" ) String description,
        @NotBlank(message = "El campo location esta vacio") Ubication location,
        @NotBlank(message = "El campo de fotos esta vacio")List<String> pictures,
        @NotBlank(message = "Los horarios estan vacio" ) List<Schedule> schedule,
        @NotBlank(message = "Los horarios estan vacios" ) List<String> phones,
        @NotBlank(message = "No se ha establecido una categoria" ) List<Category> categories,
        String clientId
        ) {
}
