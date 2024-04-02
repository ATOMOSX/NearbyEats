package co.edu.uniquindio.nearby_eats.test;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    private String userId = "660c3ebed1c60064f7797fb2";

    @Test
    public void registerUserTest() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
            "Juan",
            "Perez",
            "juanito@gmail.com",
            "123456",
            "juanito123",
            "Armenia",
                "Imagen de perfil"
        );
        String userCode = userService.register(userRegistrationDTO);
        userId = userCode;
        System.out.println("User code: " + userCode);
        Assertions.assertNotNull(userCode);
    }

    @Test
    public void loginUserTest() throws Exception {
        UserLoginDTO userLoginDTO = new UserLoginDTO("juanito123", "123456");
        String userId = userService.login(userLoginDTO);
        Assertions.assertNotNull(userId);
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(
                "Juan",
                "Perez",
                "jloaizanieto@gmail.com",
                "Armenia",
                "Imagen de perfil 2"
        );
        userService.updateUser(userId, userUpdateDTO);
        UserInformationDTO user = userService.getUser(userId);
        Assertions.assertEquals(userUpdateDTO.profilePicture(), user.profilePicture());
    }

    @Test
    public void getAllUsersTest() {
        int expectedUsers = 1;
        List<UserInformationDTO> users = userService.getAllUsers();
        Assertions.assertEquals(expectedUsers, users.size());
    }

    @Test
    public void getUserTest() throws Exception {
        UserInformationDTO user = userService.getUser(userId);
        Assertions.assertNotNull(user);
    }

    @Test
    public void sendRecoveryEmailTest() throws Exception {
        Assertions.assertDoesNotThrow(() -> userService.sendRecoveryEmail("jloaizanieto@gmail.com")); // Email must exist
    }

    @Test
    public void changePasswordTest() throws Exception {
        // TODO: Implement this test
    }

    @Test
    public void deleteUserTest() {
        Assertions.assertDoesNotThrow(() -> userService.deleteUser(userId));
    }
}
