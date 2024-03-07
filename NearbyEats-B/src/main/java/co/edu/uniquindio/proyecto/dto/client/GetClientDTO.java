package co.edu.uniquindio.proyecto.dto.client;

public record GetClientDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String profilePhoto,
        String city,
        String password
) {
}
