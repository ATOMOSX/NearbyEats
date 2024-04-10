package co.edu.uniquindio.nearby_eats.controlers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MessageDTO<String>> login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws UserLoginException {
        userService.login(userLoginDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "login user successful"));
    }
    @PostMapping("/register-user")
    public ResponseEntity<MessageDTO<String>> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException {
        userService.register(userRegistrationDTO);
        return ResponseEntity.ok().body( new MessageDTO<>(false, "register user successful"));
    }

    @PostMapping("/update-account-user")
    public ResponseEntity<MessageDTO<String>> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) throws UpdateAccountException {
        userService.updateUser(userUpdateDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "update user successful"));
    }
    @PostMapping("/delete-user")
    public ResponseEntity<MessageDTO<String>> deleteUser(@Valid @RequestBody String id) throws DeleteAccountException {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "delete user successful "));
    }

    @PostMapping("/change-password/send-recovery-email")
    public ResponseEntity<MessageDTO<String>> sendRecoveryEmail(@Valid @RequestBody String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException {
        userService.sendRecoveryEmail(email);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "send email recovery email successful"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageDTO<String>> changePassword(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException {
        userService.changePassword(userChangePasswordDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "change password user is successful"));
    }

    @PostMapping("/get-all-users")
    public ResponseEntity<MessageDTO<List<UserInformationDTO>>> getAllUsers() throws GetAllUserException {
        userService.getAllUsers();
        return ResponseEntity.ok().body(new MessageDTO<>(false, userService.getAllUsers()));
    }

    @PostMapping("/get-user")
    public ResponseEntity<MessageDTO<UserInformationDTO>> getUser(@Valid @RequestBody String id) throws GetUserException {
        userService.getUser(id);
        return ResponseEntity.ok().body(new MessageDTO<>(false, userService.getUser(id)));
    }
}
