package co.edu.uniquindio.nearby_eats.dto;

public record MessageDTO <T>(
        boolean error,
        T response
) {
}
