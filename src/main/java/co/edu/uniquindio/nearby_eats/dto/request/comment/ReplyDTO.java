package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record ReplyDTO (
        String token,
        @NotBlank(message = "text") String text,
       @NotBlank(message = "commentId")  String commentId
){
}
