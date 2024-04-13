package co.edu.uniquindio.nearby_eats.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRegistrationDTO(
       @NotBlank(message = "firstName is required") @Length(max = 55) String firstName,
       @NotBlank(message = "lastName is required") @Length(max = 55) String lastName,
       @NotBlank(message = "email is required") @Email String email,
       @NotBlank(message = "password is required") String password,
       @NotBlank(message = "nickname is required") @Length(max = 30) String nickname,
       @NotBlank(message = "city is required") String city,
       @NotBlank(message = "profilePicture is required") String profilePicture
) {

}
