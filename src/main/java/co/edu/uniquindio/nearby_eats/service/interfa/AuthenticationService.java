package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.user.ModeratorLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.response.TokenDTO;
import co.edu.uniquindio.nearby_eats.exceptions.authentication.AuthtenticationException;

public interface AuthenticationService {
    TokenDTO loginUser(UserLoginDTO userLoginDTO) throws AuthtenticationException;

    TokenDTO loginModerator(ModeratorLoginDTO moderatorLoginDTO) throws AuthtenticationException;

}
