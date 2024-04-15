package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;
import co.edu.uniquindio.nearby_eats.exceptions.authentication.AuthtenticationException;
import co.edu.uniquindio.nearby_eats.service.interfa.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationControllable {

    private final AuthenticationService authenticationService;

    @PostMapping("login-user")
    public ResponseEntity<MessageDTO<TokenDTO>> loginUser (@Valid @RequestBody UserLoginDTO userLoginDTO) throws AuthtenticationException {
        TokenDTO tokenDTO = authenticationService.loginUser(userLoginDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, tokenDTO));
    }

    @PostMapping("login-moderator")
    public ResponseEntity<MessageDTO<TokenDTO>> loginModerator (@Valid @RequestBody UserLoginDTO userLoginDTO) throws AuthtenticationException {
        TokenDTO tokenDTO = authenticationService.loginModerator(userLoginDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, tokenDTO));
    }
}
