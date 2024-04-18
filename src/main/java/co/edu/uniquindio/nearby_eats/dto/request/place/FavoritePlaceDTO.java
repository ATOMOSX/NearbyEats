package co.edu.uniquindio.nearby_eats.dto.request.place;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record FavoritePlaceDTO(
        String token,
        @NotBlank(message = "placeId is required") @Length(max = 100) String placeId
) {
}
