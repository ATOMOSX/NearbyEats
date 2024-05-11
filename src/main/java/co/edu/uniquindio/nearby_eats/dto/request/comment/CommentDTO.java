package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentDTO(
        String placeId,
        @NotBlank(message = "comment is required") @Length(max = 380) String comment,int score
) {
}
