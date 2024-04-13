package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CommentDTO(
        @NotBlank(message = "placeId is required") @Length(max = 100) String placeId,
        @NotBlank(message = "clientId is required") @Length(max = 100) String clientId,
        @NotBlank(message = "comment is required") @Length(max = 380) String comment,
        @NotBlank(message = "score is required") Integer score
) {
}
