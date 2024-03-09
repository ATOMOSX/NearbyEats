package co.edu.uniquindio.proyecto.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record AnswerCommentDTO(
        @NotBlank(message = "idComment is required")  String idComment,
        @NotBlank(message = "idPlace is required") String idPlace,
        @NotBlank(message = "idUser is required") String idUser,
        @NotBlank(message = "answer is required") String answer
) {
}
