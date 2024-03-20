package co.edu.uniquindio.proyecto.dto.comment;

import co.edu.uniquindio.proyecto.model.documents.Commentary;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

public record ItemCommentDTO(
        String commentary,
        Commentary answer,
        String firstName,
        String lastName,
        String profilePhoto,
        LocalDateTime date,
        int score
) {
}
