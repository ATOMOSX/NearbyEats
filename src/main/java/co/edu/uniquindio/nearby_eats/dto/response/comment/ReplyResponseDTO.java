package co.edu.uniquindio.nearby_eats.dto.response.comment;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public record ReplyResponseDTO(
        @NotBlank(message = "date is required") @DateTimeFormat String date,
        @NotBlank(message = "clientId is required") @Length(max = 100) String clientId,
        @NotBlank(message = "reply is required") @Length(max = 288) String reply
) {
}
