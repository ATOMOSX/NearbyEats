package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.place.CreatePlaceDTO;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.repository.PlaceRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PlaceTest {

    @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void createPlace() {
        CreatePlaceDTO createPlaceDTO = new CreatePlaceDTO(
                "Sazón criollo",
                "Lorem ipsum",
                new Ubication(4.5604933f, -75.6594419f),
                List.of("picture"),
                List.of(new Schedule("Monday", LocalTime.of(10, 00), LocalTime.of(20, 30))),
                List.of("1234567"),
                List.of(Category.RESTAURANT),
                "65fdf34f7520694d06ac400d"
        );

        Optional<Client> optionalClient = clientRepo.findById(createPlaceDTO.clientId());
        Client client = optionalClient.get();

        Place place = Place.builder()
                .name(createPlaceDTO.name())
                .description(createPlaceDTO.description())
                .location(createPlaceDTO.location())
                .pictures(createPlaceDTO.pictures())
                .schedule(createPlaceDTO.schedule())
                .phones(createPlaceDTO.phones())
                .categories(createPlaceDTO.categories())
                .creationDate(LocalDateTime.now())
                .build();

        client.setCreatedPlaces(List.of(place));
        Place place1 = placeRepo.save(place);
        Client client1 = clientRepo.save(client);
        Assertions.assertNotNull(place1);
        Assertions.assertNotNull(client1);
    }
}
