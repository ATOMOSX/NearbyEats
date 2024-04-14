package co.edu.uniquindio.nearby_eats.test;

import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PlaceTest {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private EmailService emailService;

    private final String placeId = "place1";
    private final String userId = "client1";

    @Test
    public void createPlaceTest() throws CreatePlaceException {
        PlaceCreateDTO placeCreateDTO = new PlaceCreateDTO(
                "Mocawa Plaza",
                "Los mejores precios en Hoteles",
                new Location("point", Arrays.asList(10.123, -73.456)),
                List.of("picture1.jpg", "picture2.jpg", "picture3.jpg"),
                List.of(new Schedule("Monday", "9:00", "18:00"),
                        new Schedule("Tuesday", "9:00", "18:00")),
                List.of("3207920496", "3108825866"),
                List.of(PlaceCategory.HOTEL),
                userId
        );

        Optional<Place> placeOptional = placeRepository.findById(placeCreateDTO.clientId());

        Place createPlace = placeService.createPlace(placeCreateDTO);
        Assertions.assertNotNull(placeOptional);
    }

    @Test
    public void updatePlaceTest() throws UpdatePlaceException{
        UpdatePlaceDTO updatePlaceDTO = new UpdatePlaceDTO(
                "sazón Plaza",
                "Los mejores precios en Comidas",
                new Location("point", Arrays.asList(4.560493469238281, -75.65943908691406)),
                List.of("picture1.jpg", "picture2.jpg"),
                List.of(new Schedule("Monday", "9:00", "18:00"),
                        new Schedule("Tuesday", "9:00", "18:00")),
                List.of("3207920496", "3108825866"),
                List.of(PlaceCategory.RESTAURANT),
                userId,
                placeId
        );

        Optional<Place> placeOptional = placeRepository.findById(updatePlaceDTO.placeId());
        placeService.updatePlace(updatePlaceDTO);

        Assertions.assertNotNull(placeOptional);
    }

    @Test
    public void deletePlaceTest() throws DeletePlaceException {
        DeletePlaceDTO deletePlaceDTO = new DeletePlaceDTO(
                        placeId,
                "client1"
        );

        Optional<Place> placeOptional = placeRepository.findById(deletePlaceDTO.placeId());
        Place place = placeService.deletePlace(deletePlaceDTO);

        Assertions.assertNotNull(placeOptional);
    }

    @Test
    public void getPlaceTest() throws GetPlaceException {
        Optional<Place> placeOptional = placeRepository.findById(placeId);
        PlaceResponseDTO placeResponseDTO = placeService.getPlace(placeId);
        System.out.println(placeResponseDTO);

        Assertions.assertNotNull(placeOptional);
    }

    @Test
    public void getPlacesByCategory() throws GetPlaceException {
        PlaceCategory placeCategory = PlaceCategory.HOTEL;
        List<PlaceResponseDTO> place = placeService.getPlacesByCategory(placeCategory);
        System.out.println(place);

        Assertions.assertNotNull(placeCategory);
    }

    @Test
    public void getPlacesByStatusTest() throws GetPlaceException {
        GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO = new GetPlacesByStatusByClientDTO(
                PlaceStatus.PENDING,
                userId
        );

        List<PlaceResponseDTO> placeResponseDTOS = placeService.getPlacesByStatus(getPlacesByStatusByClientDTO);
        System.out.println(placeResponseDTOS);

        Assertions.assertNotNull(getPlacesByStatusByClientDTO);
    }

    @Test
    public void getPlacesByClientIdTest() throws GetPlaceException {
        List<PlaceResponseDTO> placeResponseDTOS = placeService.getPlacesByClientId(userId);
        System.out.println(placeResponseDTOS);

        Assertions.assertNotNull(placeResponseDTOS);
    }

    @Test
    public void getPlacesByLocationTest() throws GetPlaceException {

    }

    @Test
    public void getPlacesByModeratorTest() throws GetPlaceException {

    }

    @Test
    public void getPlaceByNameTest() throws GetPlaceException {
        String name = "Exito";
        List<PlaceResponseDTO> placeResponseDTOS = placeService.getPlacesByName(name);
        System.out.println(placeResponseDTOS);

        Assertions.assertNotNull(placeResponseDTOS);
    }

    //Revisión
    @Test
    public void saveFavoritePlaceTest() throws FavoritePlaceException {
        FavoritePlaceDTO favoritePlaceDTO = new FavoritePlaceDTO(
                userId,
                placeId
        );

        Place place = placeService.saveFavoritePlace(favoritePlaceDTO);
        System.out.println(place);
        Assertions.assertNotNull(place);
    }

    //Revisión
    @Test
    public void deleteFavortitePlace() throws FavoritePlaceException {
        FavoritePlaceDTO favoritePlaceDTO = new FavoritePlaceDTO(
                userId,
                placeId
        );

        Place place = placeService.deleteFavoritePlace(favoritePlaceDTO);
        System.out.println(place);
        Assertions.assertNotNull(place);
    }


}
