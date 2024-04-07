package co.edu.uniquindio.nearby_eats.service;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;

public interface AuthenticationService {
    TokenDTO login(UserLoginDTO userLoginDTO) throws Exception;
}
