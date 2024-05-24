package co.edu.uniquindio.nearby_eats.dto.response.place;

import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public record   PlaceResponseDTO(
        @NotBlank(message = "id is required") @Length(max = 100) String id,
        @NotBlank(message = "name is required") @Length(max = 55) String name,
        @NotBlank(message = "description is required") @Length(max = 288) String description,
        @NotBlank(message = "location is required") Location location,
        @NotBlank(message = "picture is required") List<String> pictures,
        @NotBlank(message = "id is required") @DateTimeFormat List<Schedule> schedule,
        @NotBlank(message = "phones is required") List<String> phones,
        @NotBlank(message = "categories is required")  List<String> categories,
        @NotBlank(message = "revisionHistory is required") List<Review> revisionsHistory,
        String createdBy,
        String status,
        float score,
        boolean isOpen
) {
}
