package co.edu.uniquindio.nearby_eats.dto.request.place;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record GetPlacesByStatusByClientDTO(
        @NotBlank(message = "status is required") String status,
        @NotBlank(message = "clientId is required") @Length(max = 100) String clientId
) {
}
