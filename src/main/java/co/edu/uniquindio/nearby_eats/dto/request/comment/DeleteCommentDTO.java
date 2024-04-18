package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DeleteCommentDTO(
        @NotBlank(message = "commentId is required") @Length(max = 100) String commentId,
        @NotBlank(message = "token is required") @Length(max = 100) String userId
) {
}
