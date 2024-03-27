package co.edu.uniquindio.proyecto.model.implementationstest;

import co.edu.uniquindio.proyecto.dto.comment.CommentDTO;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Commentary;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.repository.CommentaryRepo;
import co.edu.uniquindio.proyecto.repository.PlaceRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class CommentaryTest {

    @Autowired
    private CommentaryRepo commentaryRepo;
    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void createCommentary() {
        CommentDTO commentDTO = new CommentDTO(
                "6600cc9e7746d640ddb0f10e",
                "6600c9288819250b51bb2d8a",
                "El mejor lugar para comer bien la comida colombiana epa",
                4,
                LocalDateTime.now()
        );

        Optional<Client> clientOptional = clientRepo.findById(commentDTO.clientId());
        Client client = clientOptional.get();

        Optional<Place> placeOptional = placeRepo.findById(commentDTO.placeId());
        Place place = placeOptional.get();

        Commentary commentary = Commentary.builder()
                .nickName(commentDTO.clientId())
                .placeId(commentDTO.placeId())
                .commentary(commentDTO.comment())
                .score(commentDTO.score())
                .date(commentDTO.date())
                .build();

        Commentary commentary1 = commentaryRepo.save(commentary);
        Assertions.assertNotNull(commentary1);
    }
}
