package co.edu.uniquindio.nearby_eats.dto.request.place;

import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdatePlaceDTO(
        @NotBlank(message = "El campo nombre es requerido" ) String name,
        @NotBlank(message = "El campo descripcion esta vacio" ) String description,
        @NotBlank(message = "El campo location esta vacio") Location location,
        @NotBlank(message = "El campo de fotos esta vacio") List<String> images,
        @NotBlank(message = "Los horarios estan vacio" ) List<Schedule> schedule,
        @NotBlank(message = "Los horarios estan vacios" ) List<String> phones,
        @NotBlank(message = "No se ha establecido una categoria" ) List<String> categories,
        @NotBlank(message = "clientId is required") String clientId,
        String placeId
) {
}
