package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.client.ClientLoginDTO;
import co.edu.uniquindio.proyecto.dto.client.ClientRegistrationDTO;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {
    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void dtoTest() {
        ClientLoginDTO clientLoginDTO = new ClientLoginDTO("", "");
    }

    @Test
    public void createClientTest() {
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
}
