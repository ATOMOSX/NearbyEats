package co.edu.uniquindio.nearby_eats.dto.request.comment;

import jakarta.validation.constraints.NotBlank;

public record ReplyDTO (
        @NotBlank(message = "respondeBy is required") String respondedBy,
        @NotBlank(message = "text") String text,
       @NotBlank(message = "commentId")  String commentId
){
}
