package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.place.CreatePlaceDTO;
import co.edu.uniquindio.proyecto.dto.place.DeletePlaceDTO;
import co.edu.uniquindio.proyecto.exceptions.place.DeletePlaceException;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
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
    public void createPlaceTest() {
        CreatePlaceDTO createPlaceDTO = new CreatePlaceDTO(
                "Sazón criollo",
                "Lorem ipsum",
                new Ubication(4.5604933f, -75.6594419f),
                List.of("picture"),
                List.of(new Schedule("Monday", LocalTime.of(10, 00), LocalTime.of(20, 30))),
                List.of("1234567"),
                List.of(Category.RESTAURANT),
                "6600c6932817b9077153bfec"
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

    @Test
    public void createPlaceTwoTest() {
        CreatePlaceDTO createPlaceDTO = new CreatePlaceDTO(
                "Éxito Hospedaje",
                "Mejores precios para todos nuestros clientes y más acercados siempre que se pueda",
                new Ubication(36.7892f, -105.4347f),
                List.of("picture", "picture2", "picture 3"),
                List.of(new Schedule("Monday", LocalTime.of(10, 00), LocalTime.of(20, 30))),
                List.of("1234567", "987654321"),
                List.of(Category.HOTEL),
                "6600cc9e7746d640ddb0f10e"
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

    @Test
    public void deletePlaceTest() throws DeletePlaceException {
        DeletePlaceDTO deletePlaceDTO = new DeletePlaceDTO(
                "66024e4d35ecb96f435959d9",
                "6600cc9e7746d640ddb0f10e"
        );

        Optional<Place> placeOptional = placeRepo.findById(deletePlaceDTO.idPlace());
        Optional<Client> clientOptional = clientRepo.findById(deletePlaceDTO.clientId());

        Client client = clientOptional.get();
        Place place = placeOptional.get();

        if (place.getStatus() == Status.INACTIVE) {
            throw new DeletePlaceException("No se puede obtener un lugar inactivo");
        }

        place.setStatus(Status.INACTIVE);
        place.setDeletionDate(LocalDateTime.now());
        int placeIndex = client.getCreatedPlaces().indexOf(place);
        client.getCreatedPlaces().set(placeIndex, null);

        placeRepo.save(place);
        clientRepo.save(client);
        Assertions.assertNotNull(placeOptional);
    }
}
