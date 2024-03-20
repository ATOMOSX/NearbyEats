package co.edu.uniquindio.proyecto.dto.comment;

import co.edu.uniquindio.proyecto.model.documents.Commentary;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ListCommentsDTO(
        String nickName,
        String commentary,
        LocalDateTime date,
        Commentary answer,
        int score
) {
}
