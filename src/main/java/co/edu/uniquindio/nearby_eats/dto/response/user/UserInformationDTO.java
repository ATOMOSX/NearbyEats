package co.edu.uniquindio.nearby_eats.dto.response.user;

import java.util.List;

public record UserInformationDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String nickname,
        String city,
        String profilePicture,
        List<String>favoritePlaces
) {
}
