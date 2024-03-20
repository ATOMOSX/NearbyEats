package co.edu.uniquindio.proyecto.dto.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record CommentDTO(
        @NotBlank(message = "clientId is required") String clientId,
        @NotBlank(message = "placeId is required") String placeId,
        @NotBlank(message = "Comment is required") String comment,
        @NotBlank(message = "Score is required") @NumberFormat int score,
        @NotBlank(message = "Date is required") @DateTimeFormat LocalDateTime date
) {
}
