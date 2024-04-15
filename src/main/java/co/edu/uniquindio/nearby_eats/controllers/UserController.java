package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.user.*;
import co.edu.uniquindio.nearby_eats.service.interfa.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<MessageDTO<String>> register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException {
        userService.register(userRegistrationDTO);
        return ResponseEntity.ok().body( new MessageDTO<>(false, "register user successful"));
    }

    @PatchMapping("/update-account-user")
    public ResponseEntity<MessageDTO<String>> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) throws UpdateAccountException {
        userService.updateUser(userUpdateDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "update user successful"));
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<MessageDTO<String>> deleteUser(@PathVariable String id) throws DeleteAccountException {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "delete user successful "));
    }

    @PostMapping("/change-password/send-recovery-email/{email}")
    public ResponseEntity<MessageDTO<String>> sendRecoveryEmail(@PathVariable String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException {
        userService.sendRecoveryEmail(email);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "send email recovery email successful"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageDTO<String>> changePassword(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException {
        userService.changePassword(userChangePasswordDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "change password user is successful"));
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<MessageDTO<List<UserInformationDTO>>> getAllUsers() throws GetAllUserException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, userService.getAllUsers()));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<MessageDTO<UserInformationDTO>> getUser(@PathVariable String id) throws GetUserException {
        return ResponseEntity.ok().body(new MessageDTO<>(false, userService.getUser(id)));
    }
}
