package co.edu.uniquindio.proyecto.dto.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record CommentDTO(
        @NotBlank(message = "Nickname is required") @Length(max = 100) String userId,
        @NotBlank(message = "Place is required") @Length(max = 100) String placeId,
        @NotBlank(message = "Comment is required") String comment,
        @NotBlank(message = "Score is required") int score,
        @NotBlank(message = "Date is required") @DateTimeFormat LocalDateTime date) {
}
