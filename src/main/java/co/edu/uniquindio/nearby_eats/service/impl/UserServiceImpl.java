package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserChangePasswordDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserLoginDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserRegistrationDTO;
import co.edu.uniquindio.nearby_eats.dto.request.user.UserUpdateDTO;
import co.edu.uniquindio.nearby_eats.dto.response.user.UserInformationDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.user.*;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.UserRole;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.service.interfa.UserService;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private Set<String> forbiddenNickName;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) throws UserLoginException {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.email());

        if (userOptional.isEmpty()){
            throw new UserLoginException("El email no existe");
        }

        if (!userOptional.get().getPassword().equals(userLoginDTO.password()))
            throw new UserLoginException("La contraseña es incorrecta");

        return "token";
    }

    @Override
    public String register(UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException {

        if (existEmail(userRegistrationDTO.email())) {
            throw new UserRegistrationException("Email already exists");
        }

        if (existNickName(userRegistrationDTO.nickname())) {
            throw new UserRegistrationException("Nickname already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());


        User user = User.builder()
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .email(userRegistrationDTO.email())
                .password(encryptedPassword) // TODO: Encrypt password
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
    public void updateUser(UserUpdateDTO userUpdateDTO) throws UpdateAccountException {
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.id());

        if (userOptional.isEmpty()){
            throw new UpdateAccountException("El id no puede estar vacio para poder actualizar el cliente");
        }

        if(existEmail(userUpdateDTO.email()))
            throw new UpdateAccountException("El email ya existe en la base de datos");

        User user = userOptional.get();
        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
        user.setEmail(userUpdateDTO.email());
        user.setCity(userUpdateDTO.city());
        user.setProfilePicture(userUpdateDTO.profilePicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws DeleteAccountException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()){
            throw new DeleteAccountException("El id del cliente a eliminar no puede ser vacío");
        }

        User user = userOptional.get();
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
    public UserInformationDTO getUser(String id) throws GetUserException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new GetUserException("id is empty");
        }

        User user = userOptional.get();

        return new UserInformationDTO(
                id,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getNickname(),
                user.getProfilePicture(),
                user.getCity()
        );
    }

    @Override
    public void sendRecoveryEmail(String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException {
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
    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException {

        Optional<User> userOptional = userRepository.findById(userChangePasswordDTO.id());

        if (userOptional.isEmpty()) {
            throw new ChangePasswordException("id user is empty");
        }

        // Verificar token
        // Obtener usuario por token
        // TODO: Encrypt password
        // Actualizar usuario

        User user = userOptional.get();
        user.setPassword(userChangePasswordDTO.newPassword());
        userRepository.save(user);
    }

    private User getUserById(UserUpdateDTO userUpdateDTO) throws Exception {
        Optional<User> user = userRepository.findById(userUpdateDTO.id());
        if (user.isPresent() && user.get().getIsActive()) {
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

    public boolean existEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean existNickName(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    private void uploadForbiddenName() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("usuarios_prohibidos.txt"))) {
            String nickName;
            while ((nickName = bufferedReader.readLine() ) != null) {
                forbiddenNickName.add(nickName.trim());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isForbiddenNickName (String nickName) {return forbiddenNickName.contains(nickName);}

}
