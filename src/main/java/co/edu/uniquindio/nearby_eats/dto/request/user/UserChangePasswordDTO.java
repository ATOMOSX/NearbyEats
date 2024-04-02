package co.edu.uniquindio.nearby_eats.dto.request.user;

public record UserChangePasswordDTO(
        String newPassword,
        String recoveryToken
) {
}
