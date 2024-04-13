package co.edu.uniquindio.nearby_eats.dto.response.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record CommentResponseDTO(
        @NotBlank(message = "id is required") @Length(max = 100) String id,
        @NotBlank(message = "date is required") String date,
        @NotBlank(message = "clientId is required") @Length(max = 100) String clientId,
        @NotBlank(message = "text is required") @Length(max = 288) String text,
        @NotBlank(message = "score is required") @Length(max = 2) Integer score,
        @NotBlank(message = "reply is required") ReplyResponseDTO reply
) {
}
