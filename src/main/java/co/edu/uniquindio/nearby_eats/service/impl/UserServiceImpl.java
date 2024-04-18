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
import co.edu.uniquindio.nearby_eats.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private Set<String> forbiddenNickName = new HashSet<>();
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
        uploadForbiddenName();
    }

    @Override
    public String register(UserRegistrationDTO userRegistrationDTO) throws UserRegistrationException {

        if (existEmail(userRegistrationDTO.email())) {
            throw new UserRegistrationException("Email already exists");
        }

        if (existNickName(userRegistrationDTO.nickname())) {
            throw new UserRegistrationException("Nickname already exists");
        }

        if(isForbiddenNickName(userRegistrationDTO.nickname()))
            throw new UserRegistrationException("El nickname es inválido");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userRegistrationDTO.password());

        User user = User.builder()
                .firstName(userRegistrationDTO.firstName())
                .lastName(userRegistrationDTO.lastName())
                .email(userRegistrationDTO.email())
                .password(encryptedPassword)
                .nickname(userRegistrationDTO.nickname())
                .city(userRegistrationDTO.city())
                .profilePicture(userRegistrationDTO.profilePicture())
                .isActive(true)
                .role(UserRole.CLIENT.name())
                .build();

        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) throws UpdateAccountException {
        Jws<Claims> jws = jwtUtils.parseJwt(userUpdateDTO.token());
        String userId = jws.getPayload().get("id").toString();
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()){
            throw new UpdateAccountException("El id no puede estar vacio para poder actualizar el cliente");
        }

        if(!userOptional.get().getEmail().equals(userUpdateDTO.email())) {
            if(existEmail(userUpdateDTO.email()))
                throw new UpdateAccountException("El email ya existe en la base de datos");
        }

        User user = userOptional.get();
        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
        user.setEmail(userUpdateDTO.email());
        user.setCity(userUpdateDTO.city());
        user.setProfilePicture(userUpdateDTO.profilePicture());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String token) throws DeleteAccountException {
        Jws<Claims> jws = jwtUtils.parseJwt(token);
        String userId = jws.getPayload().get("id").toString();
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()){
            throw new DeleteAccountException("El id del cliente a eliminar no puede ser vacío");
        }

        User user = userOptional.get();
        user.setIsActive(false);

        userRepository.save(user);
    }

    @Override
    public List<UserInformationDTO> getAllUsers() {
        List<User> users = userRepository.findAllByIsActiveAndRole(true, "CLIENT");
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
        Optional<User> clientOptional = userRepository.findByEmail(email);

        if (clientOptional.isEmpty()) {
            throw new SendRecoveryEmailException("El email no puede ser vacio");
        }

        String token = jwtUtils.generateToken(email, null);

        emailService.sendEmail(new EmailDTO("Cambio de contraseña de NearbyEats",
                "Para cambiar la contraseña ingrese al siguiente enlace http://localhost:8080/api/user/change-password"+token, email));

    }

    @Override
    public void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws ChangePasswordException {

        Optional<User> userOptional = userRepository.findById(userChangePasswordDTO.id());

        jwtUtils.parseJwt(userChangePasswordDTO.recoveryToken());

        if (userOptional.isEmpty()) {
            throw new ChangePasswordException("id user is empty");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userChangePasswordDTO.newPassword());

        User user = userOptional.get();
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    private User getUserById(UserUpdateDTO userUpdateDTO) throws Exception {
        Jws<Claims> jws = jwtUtils.parseJwt(userUpdateDTO.token());
        String userId = jws.getPayload().get("id").toString();
        Optional<User> user = userRepository.findById(userId);
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
        ClassPathResource resource = new ClassPathResource("usuarios_prohibidos.txt");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                forbiddenNickName.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isForbiddenNickName (String nickName) {return forbiddenNickName.contains(nickName.trim().toLowerCase());}

}
