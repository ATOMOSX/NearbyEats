package co.edu.uniquindio.nearby_eats.dto.response.place;

import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;

import java.util.List;

public record PlaceResponseDTO(
        String id,
        String name,
        String description,
        Location location,
        List<String> pictures,
        List<Schedule> schedule,
        List<String> phones,
        List<String> categories,
        List<Review> revisionsHistory
) {
}
