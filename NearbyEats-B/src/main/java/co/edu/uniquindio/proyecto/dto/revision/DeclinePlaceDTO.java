package co.edu.uniquindio.proyecto.dto.revision;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DeclinePlaceDTO(
        @NotBlank(message = "idPlace is required") @Length(max = 100) String idPlace,
        @NotBlank(message = "idModerator is required" )String idModerator,
        @NotBlank(message = "Describe el motivo por el cual fue rechazado el lugar" ) String description

) {
}
