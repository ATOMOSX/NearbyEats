package co.edu.uniquindio.proyecto.dto.comment;

import co.edu.uniquindio.proyecto.model.documents.Comentary;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemCommentDTO(
        @NotBlank(message = "Id is required") @Length(max = 100) String id,
        @NotBlank(message = "A commentary is required") @Length(max = 100) String commentary,
        @NotBlank(message = "A answer is required") @Length(max = 100) Comentary answer,
        @NotBlank(message = "First name is required") @Length(max = 100) String firstName,
        @NotBlank(message = "Last name is required") @Length(max = 100) String lastName,
        @NotBlank(message = "Profile photo is required") String profilePhoto,
        @NotBlank(message = "Date is required") String date,
        @NotBlank(message = "Score is required") String score
) {
}
