package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.UserRole;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.EmailService;
import co.edu.uniquindio.nearby_eats.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) throws Exception {
        Optional<User> user = userRepository.findByNicknameAndIsActive(userLoginDTO.nickname(), true);
        if (user.isPresent()) {
            // TODO: Decrypt password
            if (user.get().getPassword().equals(userLoginDTO.password())) {
                return user.get().getId(); // TODO: Generate and return token
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public String register(UserRegistrationDTO userRegistrationDTO) throws Exception {

        if (userRepository.existsByEmailAndIsActive(userRegistrationDTO.email(), true)) {
            throw new Exception("Email already exists");
        }

        if (userRepository.existsByNicknameAndIsActive(userRegistrationDTO.nickname(), true)) {
            throw new Exception("Nickname already exists");
        }

        User user = User.builder()
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .email(userRegistrationDTO.email())
                .password(userRegistrationDTO.password()) // TODO: Encrypt password
                .nickname(userRegistrationDTO.nickname()) // TODO: Verify banned user names
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .isActive(true)
                .roles(List.of(UserRole.CLIENT.name()))
                .build();

        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Override
    public void updateUser(String id, UserUpdateDTO userUpdateDTO) throws Exception {
        User user = getUserById(id);

        if (!user.getEmail().equals(userUpdateDTO.email())) {
            if (userRepository.existsByEmail(userUpdateDTO.email()))
                throw new Exception("Email already exists");
        }

        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
        user.setEmail(userUpdateDTO.email());
        user.setCity(userUpdateDTO.city());
        user.setProfilePicture(userUpdateDTO.profilePicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        User user = getUserById(id);
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public List<UserInformationDTO> getAllUsers() {
        List<User> users = userRepository.findAllByIsActive(true);
        return users.stream()
                .map(this::convertToUserInformationDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UserInformationDTO getUser(String id) throws Exception {
        User user = getUserById(id);
        return convertToUserInformationDTO(user);
    }

    @Override
    public void sendRecoveryEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            // TODO: Generate token and replace 123

            emailService.sendEmail(
                    new EmailDTO(
                            email,
                            "Cambio de contraseña - Nearby Eats",
                            "Para cambiar tu contraseña, haz clic en el siguiente enlace: http://localhost:8080/recovery?token=123"
                    )
            );
        }
    }

    @Override
    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws Exception {
        // Verificar token
        // Obtener usuario por token
        // TODO: Encrypt password
        // Actualizar usuario
    }

    private User getUserById(String id) throws Exception {
        Optional<User> user = userRepository.findByIdAndIsActive(id, true);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    private UserInformationDTO convertToUserInformationDTO(User user) {
        return new UserInformationDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getNickname(),
                user.getCity(),
                user.getProfilePicture()
        );
    }
}
