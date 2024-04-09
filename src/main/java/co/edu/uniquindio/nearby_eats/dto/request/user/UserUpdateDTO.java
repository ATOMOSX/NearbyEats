package co.edu.uniquindio.nearby_eats.dto.request.user;

public record UserUpdateDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String city,
        String profilePicture
) {}
