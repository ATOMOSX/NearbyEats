package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;
import co.edu.uniquindio.nearby_eats.model.docs.User;
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
    public TokenDTO login(UserLoginDTO userLoginDTO) throws Exception {
        Optional<User> clientOptional = userRepository.findByEmail(userLoginDTO.email());
        if (clientOptional.isEmpty()) {
            throw new Exception("El email no se encuentra en la base de datos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = clientOptional.get();
        if( !passwordEncoder.matches(userLoginDTO.password(), user.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("nombre", user.getFirstName());
        map.put("rol", user.getRoles());
        return new TokenDTO( jwtUtils.generateToken(user.getEmail(), map) );
    }
}
