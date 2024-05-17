package co.edu.uniquindio.nearby_eats.dto.request.place;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdatePlaceDTO(
        @NotBlank(message = "name is required" ) String name,
        @NotBlank(message = "description is required" ) String description,
        Location location,
        List<String> images,
        List<Schedule> schedule,
        List<String> phones,
        List<String> categories,
        @NotBlank(message = "clientId is required") String clientId,
        @NotBlank(message = "placeId is required") String placeId
) {
}
