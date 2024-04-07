package co.edu.uniquindio.nearby_eats.dto.request.review;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;

public record PlaceReviewDTO(
        PlaceStatus action,
        String commentary,
        String moderatorId,
        String placeId
) {
}
