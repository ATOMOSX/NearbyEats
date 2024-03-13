package co.edu.uniquindio.proyecto.dto.place;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record CreatePlaceDTO(
        @NotBlank(message = "El campo nombre es requerido" ) String name,
        @NotBlank(message = "El campo descripcion esta vacio" ) String description,
        @NotBlank(message = "El campo location esta vacio") String ubucation,
        @NotBlank(message = "El campo de fotos esta vacio")List<String> pictures,
        @NotBlank(message = "Los horarios estan vacio" ) List<LocalDateTime> schedule,
        @NotBlank(message = "Los horarios estan vacios" ) List<String> phones,
        @NotBlank(message = "No se ha establecido una categoria" ) List<String> categories
        ) {
}
