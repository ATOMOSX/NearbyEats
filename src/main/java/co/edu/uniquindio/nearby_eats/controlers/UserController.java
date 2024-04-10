package co.edu.uniquindio.nearby_eats.controlers;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.user.*;
import co.edu.uniquindio.nearby_eats.service.interfa.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login-user")
    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws UserLoginException {
        return null;
    }
    @PostMapping("/register-user")
    public String register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException {
        return null;
    }

    @PostMapping("/update-account-user")
    void updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) throws UpdateAccountException {

    }
    @PostMapping("/delete-user")
    void deleteUser(@Valid @RequestBody String id) throws DeleteAccountException {

    }

    @PostMapping("/change-password/send-recovery-email")
    void sendRecoveryEmail(@Valid @RequestBody String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException {

    }

    @PostMapping("/change-password")
    void changePassword(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException {

    }

    @PostMapping("/get-all-users")
    List<UserInformationDTO> getAllUsers() throws GetAllUserException {
        return null;
    }

    @PostMapping("/get-user")
    UserInformationDTO getUser(@Valid @RequestBody String id) throws GetUserException {
        return null;
    }


}
