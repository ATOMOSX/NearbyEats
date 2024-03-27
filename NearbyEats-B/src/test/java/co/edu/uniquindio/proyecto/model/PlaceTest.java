package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.dto.place.*;
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
                "6600c6932817b9077153bfec",
                Status.WAITING
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
                "6600cc9e7746d640ddb0f10e",
                Status.WAITING
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
    public void createPlaceThreeTest() {
        CreatePlaceDTO createPlaceDTO = new CreatePlaceDTO(
                "Mega Perro",
                "Los mejores perros de la ciudad para compartir con tus familiares o amigos favoritos",
                new Ubication(12.3456f, -67.8910f),
                List.of("picture", "picture2", "picture 3"),
                List.of(new Schedule("Monday", LocalTime.of(10, 00), LocalTime.of(20, 30))),
                List.of("1234567", "987654321"),
                List.of(Category.HOTEL),
                "6600cc9e7746d640ddb0f10e",
                Status.WAITING
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

    @Test
    public void updatePlaceTest() {
        UpdatePlaceDto updatePlaceDto = new UpdatePlaceDto(
                "66035b13be401d4201e38d37",
                "Mega Dog Dog Hot",
                "Los mejores perros de la ciudad para compartir con tus familiares o amigos favoritos",
                new Ubication(12.3456f, -67.8910f),
                List.of("picture", "picture2", "picture 3"),
                List.of(new Schedule("Monday", LocalTime.of(10, 00), LocalTime.of(20, 30))),
                List.of("1234567", "987654321"),
                List.of(Category.HOTEL)
        );

        Optional<Place> placeOptional = placeRepo.findById(updatePlaceDto.id());

        Place place = placeOptional.get();
        place.setName(updatePlaceDto.name());
        place.setDescription(updatePlaceDto.description());
        place.setLocation(updatePlaceDto.location());
        place.setPictures(updatePlaceDto.pictures());
        place.setSchedule(updatePlaceDto.schedule());
        place.setPhones(updatePlaceDto.phones());
        place.setCategories(updatePlaceDto.categories());

        placeRepo.save(place);
        Assertions.assertNotNull(placeOptional);
    }

    @Test
    public void listPlaceByCategoryTest() {
        Category category = Category.HOTEL;

        List<ListPlaceDTO> placeList = placeRepo.findByCategory(category);
        System.out.println(placeList);
        Assertions.assertNotNull(placeList);
    }

    @Test
    public void listPlaceByLocationTest() {
        Ubication ubication = new Ubication(36.7892f, -105.4347f);

        List<ListPlaceDTO> placeList = placeRepo.findByLocation(ubication);
        System.out.println(placeList);
        Assertions.assertNotNull(placeList);
    }

    @Test
    public void listPlaceByNameTest() {
        // podemos agregar cualquier palabra que contenga y nos arrojará la consulta como hospedaje, exito, etc.
        String name = "exito";

        List<ListPlaceDTO> placeList = placeRepo.findByName(name);
        System.out.println(placeList);
        Assertions.assertNotNull(placeList);
    }

    @Test
    public void filterStatusPlaceTest() {
        List<Place> places = placeRepo.findByStatus(Status.ACTIVE);

       List<ItemPlaceStatusDTO> placesMap = places.stream().map(
                p -> new ItemPlaceStatusDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription())).toList();

        System.out.println(placesMap);
        Assertions.assertNotNull(placesMap);
    }

    //Resvisar este test
    @Test
    public void listPlaceOwnerTest() {
//        String idClient = "6600cc9e7746d640ddb0f10e";
//
//        List<ItemPlaceOwnerDTO> placeOwnerDTOS = clientRepo.findPlacesById(idClient);
//        System.out.println(placeOwnerDTOS);
    }

    @Test
    public void getPlace() {
        String idPlace = "66024e4d35ecb96f435959d9";

        Optional<Place> placeOptional = placeRepo.findById(idPlace);
        Place place = placeOptional.get();

        GetPlaceDTO getPlaceDTO = new GetPlaceDTO(
                idPlace,
                place.getName(),
                place.getDescription(),
                place.getLocation(),
                place.getPictures(),
                place.getSchedule(),
                place.getPhones(),
                place.getCategories(),
                place.getRevisionsHistory()
        );
        System.out.println(getPlaceDTO);
        Assertions.assertNotNull(place);
    }
}
