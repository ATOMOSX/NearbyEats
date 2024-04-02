package co.edu.uniquindio.nearby_eats.dto.request.user;

public record UserRegistrationDTO(
   String firstName,
   String lastName,
   String email,
   String password,
   String nickname,
   String city,
   String profilePicture
) {}
