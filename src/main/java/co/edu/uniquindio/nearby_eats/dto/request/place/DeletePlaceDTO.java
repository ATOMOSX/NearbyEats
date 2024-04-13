package co.edu.uniquindio.nearby_eats.dto.request.place;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DeletePlaceDTO(
        @NotBlank(message = "placeId is required")  String placeId,
        @NotBlank(message = "clientID is required")  String clientId
) {
}
