package co.edu.uniquindio.nearby_eats.test;

import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.user.GetAllUserException;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.service.interfa.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final String userId = "client1";

    @Test
    public void loginUserTest() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("atomos29@correo.com", "$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW");
    }


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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());
        User user = User.builder()
                .id("client2")
                .nickname(userRegistrationDTO.nickname())
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .password(encryptedPassword)
                .email(userRegistrationDTO.email())
                .build();

        User user1 = userRepository.save(user);
        System.out.println(user1.getId());
        Assertions.assertNotNull(user1);
    }

    @Test
    public void registerTwoUserTest() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
                "Pedrito",
                "Pérez",
                "pedir",
                "pedri@correo.com",
                "yose.jpg",
                "Montenegro",
                "elpepe"
        );

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());
        User user = User.builder()
                .id("client3")
                .nickname(userRegistrationDTO.nickname())
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .password(encryptedPassword)
                .email(userRegistrationDTO.email())
                .build();

        User user1 = userRepository.save(user);
        System.out.println(user1.getId());
        Assertions.assertNotNull(user1);
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(
                "client3",
                "Perez",
                "jloaizanieto@gmail.com",
                "Armenia",
                "Imagen de perfil 2",
                "to.png"
        );

        Optional<User> clientOptional = userRepository.findById(userUpdateDTO.id());

        User user = clientOptional.get();
        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
        user.setEmail(userUpdateDTO.email());
        user.setCity(userUpdateDTO.city());
        user.setProfilePicture(userUpdateDTO.profilePicture());
        userRepository.save(user);

        Assertions.assertNotNull(user);
    }

    @Test
    public void getAllUsersTest() throws GetAllUserException {
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
    public void sendRecoveryEmailTest() {
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
