package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.client.ClientRegistrationDTO;
import co.edu.uniquindio.proyecto.exceptions.client.ClientRegistrationException;
import co.edu.uniquindio.proyecto.services.interfaces.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void registrarTest() throws ClientRegistrationException{

        ClientRegistrationDTO clientRegistrationDTO = new ClientRegistrationDTO(
                "Juan David",
                "López Muñoz",
                "Atomos",
                "atomos29@correo.com",
                "yo.jpg",
                "Armenia",
                "juandis"
        );
//        Assertions.assertThrows(ClientRegistrationException.)


    }
}
