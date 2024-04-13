package co.edu.uniquindio.nearby_eats.dto.request.review;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PlaceReviewDTO(
        @NotBlank(message = "action is required") PlaceStatus action,
        @NotBlank(message = "commentary is required") @Length(max = 100) String commentary,
        @NotBlank(message = "moderatorId is required") @Length(max = 100) String moderatorId,
        @NotBlank(message = "placeId is required") @Length(max = 100) String placeId
) {
}
