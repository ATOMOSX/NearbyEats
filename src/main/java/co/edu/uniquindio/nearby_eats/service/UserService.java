package co.edu.uniquindio.nearby_eats.service;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;

import java.util.List;

public interface UserService {

    String login(UserLoginDTO userLoginDTO) throws Exception;

    String register(UserRegistrationDTO userRegistrationDTO) throws Exception;

    void updateUser(String id, UserUpdateDTO userUpdateDTO) throws Exception;

    void deleteUser(String id) throws Exception;

    List<UserInformationDTO> getAllUsers();

    UserInformationDTO getUser(String id) throws Exception;

    void sendRecoveryEmail(String email) throws Exception;

    void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws Exception;

}
