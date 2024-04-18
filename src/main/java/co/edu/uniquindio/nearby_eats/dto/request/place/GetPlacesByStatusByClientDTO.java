package co.edu.uniquindio.nearby_eats.dto.request.place;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record GetPlacesByStatusByClientDTO(
        @NotBlank(message = "status is required") String status,
        String token
) {
}
