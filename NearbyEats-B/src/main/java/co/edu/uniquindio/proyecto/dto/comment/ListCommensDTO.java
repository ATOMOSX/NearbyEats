package co.edu.uniquindio.proyecto.dto.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ListCommensDTO(
        @NotBlank(message = "idClient is required") @Length(max = 100) String idClient,
        @NotBlank(message = "idComment is required") @Length(max = 100) String idComment
) {
}
