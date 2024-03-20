package co.edu.uniquindio.proyecto.dto.comment;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record AnswerCommentDTO(
        @NotBlank(message = "idComment is required") String idComment,
        @NotBlank(message = "idPlace is required") String idPlace,
        @NotBlank(message = "idUser is required") String idUser,
        @NotBlank(message = "answer is required") String answer,
        @NotBlank(message = "Date is required") @DateTimeFormat LocalDateTime date
) {
}
