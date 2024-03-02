package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.ClientLoginDTO;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
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
        Client client = Client.builder()
                .nickname("nickname").build();
        clientRepo.save(client);
    }
}
