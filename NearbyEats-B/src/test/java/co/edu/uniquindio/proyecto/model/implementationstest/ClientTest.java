package co.edu.uniquindio.proyecto.model.implementationstest;

import co.edu.uniquindio.proyecto.dto.client.*;
import co.edu.uniquindio.proyecto.dto.email.EmailDTO;
import co.edu.uniquindio.proyecto.exceptions.client.DeleteAccountException;
import co.edu.uniquindio.proyecto.exceptions.client.GetClientException;
import co.edu.uniquindio.proyecto.exceptions.client.SendRecoveryEmailException;
import co.edu.uniquindio.proyecto.exceptions.email.EmailServiceException;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClientTest {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private EmailService emailService;
    private final String idClient = "6602055e1000a52f3687e35d";

    @Test
    public void loginClientTest() {
        ClientLoginDTO clientLoginDTO = new ClientLoginDTO("", "");
    }

    @Test
    public void registerClientTest() {
        ClientRegistrationDTO clientRegistrationDTO = new ClientRegistrationDTO(
                "Juan David",
                "López Muñoz",
                "Atomos",
                "atomos29@correo.com",
                "yo.jpg",
                "Armenia",
                "juandis"
        );

        Client client = Client.builder()
                .nickname(clientRegistrationDTO.nickname())
                .city(clientRegistrationDTO.city())
                .profilePhoto(clientRegistrationDTO.profilePhoto())
                .firstName(clientRegistrationDTO.firstName())
                .lastName(clientRegistrationDTO.lastName())
                .password(clientRegistrationDTO.password())
                .status(Status.ACTIVE)
                .email(clientRegistrationDTO.email())
                .build();

        Client client1 = clientRepo.save(client);
        System.out.println(client1.getId());
        Assertions.assertNotNull(client1);
    }

    @Test
    public void createClientTwoTest() {
        ClientRegistrationDTO clientRegistrationDTO = new ClientRegistrationDTO(
                "Pedrito",
                "Pérez",
                "pedir",
                "pedri@correo.com",
                "yose.jpg",
                "Montenegro",
                "elpepe"
        );

        Client client = Client.builder()
                .nickname(clientRegistrationDTO.nickname())
                .city(clientRegistrationDTO.city())
                .profilePhoto(clientRegistrationDTO.profilePhoto())
                .firstName(clientRegistrationDTO.firstName())
                .lastName(clientRegistrationDTO.lastName())
                .password(clientRegistrationDTO.password())
                .status(Status.ACTIVE)
                .email(clientRegistrationDTO.email())
                .build();

        Client client1 = clientRepo.save(client);
        System.out.println(client1.getId());
        Assertions.assertNotNull(client1);
    }

    @Test
    public void createClientThreeTest() {
        ClientRegistrationDTO clientRegistrationDTO = new ClientRegistrationDTO(
                "Daniela",
                "Gómez",
                "danfgg",
                "danissss@correo.com",
                "mifoto.jpg",
                "Ibagué",
                "mazorca"
        );

        Client client = Client.builder()
                .nickname(clientRegistrationDTO.nickname())
                .city(clientRegistrationDTO.city())
                .profilePhoto(clientRegistrationDTO.profilePhoto())
                .firstName(clientRegistrationDTO.firstName())
                .lastName(clientRegistrationDTO.lastName())
                .password(clientRegistrationDTO.password())
                .status(Status.ACTIVE)
                .email(clientRegistrationDTO.email())
                .build();

        Client client1 = clientRepo.save(client);
        System.out.println(client1.getId());
        Assertions.assertNotNull(client1);
    }

    @Test
    public void updateAccountClient()  {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO(
                "6600c6932817b9077153bfec",
                "Juan Pablo",
                "Velez",
                "lamona@corroe.com",
                "yo.png",
                "Circasia"
        );

        Optional<Client> clientOptional = clientRepo.findById(updateAccountDTO.id());

        Client client = clientOptional.get();
        client.setFirstName(updateAccountDTO.firstName());
        client.setLastName(updateAccountDTO.lastName());
        client.setEmail(updateAccountDTO.email());
        client.setCity(updateAccountDTO.city());
        client.setProfilePhoto(updateAccountDTO.profilePhoto());
        clientRepo.save(client);

        Assertions.assertNotNull(client);
    }

    @Test
    public void deleteAccountClientTest () throws DeleteAccountException {
        Optional<Client> clientOptional = clientRepo.findById(idClient);

        if (clientOptional.isEmpty()){
            throw new DeleteAccountException("El id del cliente a eliminar no puede ser vacío");
        }

        Client client = clientOptional.get();
        client.setStatus(Status.INACTIVE);
        clientRepo.save(client);

        Assertions.assertNotNull(client);
    }

    @Test
    public void sendRecoveryEmailClientTest() throws SendRecoveryEmailException, MessagingException, EmailServiceException {
        String email = "juand.lopezm@uqvirtual.edu.co";

        Optional<Client> clientOptional = clientRepo.findByEmail(email);

        emailService.sendEmail(new EmailDTO("Cambio de contraseña de NearbyEats",
                "Para cambiar la contraseña ingrese al siguiente enlace http://......./params ", email));

        Assertions.assertNotNull(clientOptional);
    }

    @Test
    public void changePasswordClientTest() {

    }

    @Test
    public void getClientTest() throws GetClientException {
        String id = "6600c6932817b9077153bfec";

        Optional<Client> clientOptional = clientRepo.findById(id);

        Assertions.assertNotNull(clientOptional.orElse(null), "El cliente no puede ser nulo");

        Client client = clientOptional.get();

        if (client.getStatus() == Status.INACTIVE){
            throw new GetClientException("No se puede obtener un cliente inactivo");
        }

        GetClientDTO getClientDTO = new GetClientDTO(
                idClient,
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getNickname(),
                client.getProfilePhoto(),
                client.getCity(),
                client.getPassword()
        );

        System.out.println(getClientDTO);
    }

    @Test
    public void listClients() {
        List<Client> clients = clientRepo.findByStatus(Status.ACTIVE);

        clients.stream().map(
                c -> new ItemClientDTO(c.getId(),
                        c.getFirstName(),
                        c.getLastName(),
                        c.getProfilePhoto(),
                        c.getNickname(),
                        c.getEmail(),
                        c.getCity())).toList();

        System.out.println(clients);
        Assertions.assertNotNull(clients);
    }
}
