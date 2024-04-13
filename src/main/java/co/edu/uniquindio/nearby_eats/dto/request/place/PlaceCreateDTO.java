package co.edu.uniquindio.nearby_eats.dto.request.place;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PlaceCreateDTO(
        @NotBlank(message = "name is required" ) String name,
        @NotBlank(message = "description is required" ) String description,
        @NotBlank(message = "location is required") Location location,
        @NotBlank(message = "images is empty") List<String> images,
        @NotBlank(message = "this schedule is empty" ) List<Schedule> schedule,
        @NotBlank(message = "this phones is empty" ) List<String> phones,
        @NotBlank(message = "No category has been established" ) List<String> categories,
        @NotBlank(message = "clientId is required") String clientId
) {
}
