package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.request.user.ModeratorLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;
import co.edu.uniquindio.nearby_eats.exceptions.authentication.AuthtenticationException;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.UserRole;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.AuthenticationService;
import co.edu.uniquindio.nearby_eats.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Override
    public TokenDTO loginUser(UserLoginDTO userLoginDTO) throws AuthtenticationException {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.email());
        if (userOptional.isEmpty()) {
            throw new AuthtenticationException("El email no se encuentra en la base de datos");
        }
        if(userOptional.get().getRole().equals(UserRole.MODERATOR))
            throw new AuthtenticationException("Un usuario de tipo moderador debe ir al enlace http://localhost:8080/api/user/login-moderator");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userOptional.get();
        if( !passwordEncoder.matches(userLoginDTO.password(), user.getPassword()) ) {
            throw new AuthtenticationException("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getFirstName());
        map.put("role", user.getRole());
        return new TokenDTO( jwtUtils.generateToken(user.getEmail(), map) );
    }

    @Override
    public TokenDTO loginModerator(ModeratorLoginDTO moderatorLoginDTO) throws AuthtenticationException {
        Optional<User> userOptional = userRepository.findByEmail(moderatorLoginDTO.email());
        if (userOptional.isEmpty()) {
            throw new AuthtenticationException("El email no se encuentra en la base de datos");
        }
        if(userOptional.get().getRole().equals(UserRole.CLIENT))
            throw new AuthtenticationException("Un usuario de tipo cliente debe ir al enlace http://localhost:8080/api/user/login-user");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userOptional.get();
        if( !passwordEncoder.matches(moderatorLoginDTO.password(), user.getPassword()) ) {
            throw new AuthtenticationException("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("name", user.getFirstName());
        map.put("role", user.getRole());
        return new TokenDTO( jwtUtils.generateToken(user.getEmail(), map) );
    }
}
