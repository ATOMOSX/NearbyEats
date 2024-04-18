package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import co.edu.uniquindio.nearby_eats.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Set<String> bannedNames = new HashSet<>();
    private final JwtUtils jwtUtils;

    public PlaceServiceImpl(PlaceRepository placeRepository, UserRepository userRepository, EmailService emailService
            , JwtUtils jwtUtils) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;

        try {
            loadBannedNames();
        } catch (IOException e) {
            log.error("Error loading banned names", e);
        }
    }

    @Override
    public Place createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException {

        if (placeRepository.existsByName(placeCreateDTO.name())) {
            throw new CreatePlaceException("Ya existe un lugar con ese nombre");
        }

        if (isBannedName(placeCreateDTO.name())) {
            throw new CreatePlaceException("El nombre del lugar no es permitido");
        }

        User user = userRepository.findById(placeCreateDTO.clientId()).orElse(null);
        if (user == null) {
            throw new CreatePlaceException("El cliente no existe");
        }

        Place place = Place.builder()
                .name(placeCreateDTO.name())
                .description(placeCreateDTO.description())
                .location(placeCreateDTO.location())
                .images(placeCreateDTO.images())
                .schedules(placeCreateDTO.schedule())
                .phones(placeCreateDTO.phones())
                .categories(placeCreateDTO.categories())
                .status(PlaceStatus.PENDING.name())
                .createdBy(placeCreateDTO.clientId())
                .creationDate(LocalDateTime.now().toString())
                .build();

        Place savedPlace = placeRepository.save(place);
        user.getCreatedPlaces().add(savedPlace.getId());
        userRepository.save(user);
        return savedPlace;
    }

    private boolean isBannedName(String name) {
        return bannedNames.contains(name.trim().toLowerCase());
    }

    @Override
    public Place updatePlace(UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException {
        Optional<Place> placeOptional = placeRepository.findById(updatePlaceDTO.placeId());

        if (placeOptional.isEmpty() || placeOptional.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new UpdatePlaceException("El lugar no existe");
        }

        if (isBannedName(updatePlaceDTO.name())) {
            throw new UpdatePlaceException("El nombre del lugar no es permitido");
        }

        Place updatedPlace = placeOptional.get();
        updatedPlace.setName(updatePlaceDTO.name());
        updatedPlace.setDescription(updatePlaceDTO.description());
        updatedPlace.setLocation(updatePlaceDTO.location());
        updatedPlace.setImages(updatePlaceDTO.images());
        updatedPlace.setSchedules(updatePlaceDTO.schedule());
        updatedPlace.setPhones(updatePlaceDTO.phones());
        updatedPlace.setCategories(updatePlaceDTO.categories());

        Place place = placeRepository.save(updatedPlace);
        return place;
    }

    @Override
    public Place deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {
        Optional<Place> placeOptional = placeRepository.findById(deletePlaceDTO.placeId());

        if (placeOptional.isEmpty() || placeOptional.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new DeletePlaceException("El lugar no existe");
        }

        Place deletedPlace = placeOptional.get();
        deletedPlace.setStatus(PlaceStatus.DELETED.name());
        deletedPlace.setDeletionDate(LocalDateTime.now().toString());

        Optional<User> optionalUser = userRepository.findById(deletePlaceDTO.clientId());
        User user = optionalUser.get();

        int placeIndex = user.getCreatedPlaces().indexOf(deletedPlace.getId());
        user.getCreatedPlaces().set(placeIndex, null);
        userRepository.save(user);
        Place place = placeRepository.save(deletedPlace);
        return place;
    }

    @Override
    public PlaceResponseDTO getPlace(String placeId) throws GetPlaceException {
        Optional<Place> placeOptional = placeRepository.findById(placeId);

        if (placeOptional.isEmpty() || placeOptional.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new GetPlaceException("El lugar no existe");
        }

        return convertToPlaceResponseDTO(placeOptional.get());
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByCategory(PlaceCategory category) {
        List<Place> places = placeRepository.findAllByCategoriesContaining(category);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByStatus(GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO) {
        List<Place> places = placeRepository.findAllByStatusAndCreatedBy(getPlacesByStatusByClientDTO.status(), getPlacesByStatusByClientDTO.clientId());
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByClientId(String clientId) throws GetPlaceException {

        if (!userRepository.existsById(clientId)) {
            throw new GetPlaceException("El cliente no existe");
        }

        List<Place> places = placeRepository.findAllByCreatedBy(clientId);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    // TODO: Organizar método, para ingresar una ubicacoión aproximada
    @Override
    public List<PlaceResponseDTO> getPlacesByLocation(Location location) {
        List<Place> places = placeRepository.findAllByLocation(location);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    // TODO: Crear los moderadores el el dataseet para poder hacer la prueba del método
    @Override
    public List<PlaceResponseDTO> getPlacesByModerator(GetPlacesByModeratorDTO getPlacesByModeratorDTO) throws GetPlaceException {
        return placeRepository.getPlacesByStatusByModerator(getPlacesByModeratorDTO.status(),
                getPlacesByModeratorDTO.moderatorId());
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByName(String name) throws GetPlaceException {
        List<Place> places = placeRepository.findAllByName(name);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    @Override
    public Place saveFavoritePlace(FavoritePlaceDTO favoritePlaceDTO) throws FavoritePlaceException {

        Optional<Place> placeOptional = placeRepository.findById(favoritePlaceDTO.placeId());
        if(placeOptional.isEmpty())
            throw new FavoritePlaceException("El lugar no existe");

        Optional<User> userOptional = userRepository.findById(favoritePlaceDTO.userId());
        if(userOptional.isEmpty())
            throw new FavoritePlaceException("El cliente no existe");

        User user = userOptional.get();
        Place place = placeOptional.get();
        user.getFavoritePlaces().add(place.getId());
        userRepository.save(user);
        return place;
    }

    @Override
    public Place deleteFavoritePlace(FavoritePlaceDTO deleteFavoritePlaceDTO) throws FavoritePlaceException {
        Optional<User> userOptional = userRepository.findById(deleteFavoritePlaceDTO.userId());
        if(userOptional.isEmpty())
            throw new FavoritePlaceException("El cliente no existe");

        Optional<Place> placeOptional = placeRepository.findById(deleteFavoritePlaceDTO.placeId());
        if(placeOptional.isEmpty())
            throw new FavoritePlaceException("El lugar no existe");

        User user = userOptional.get();
        Place place = placeOptional.get();

        user.getFavoritePlaces().remove(place.getId());
        userRepository.save(user);
        return place;
    }

    @Override
    public void reviewPlace(PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException, MessagingException, EmailServiceException {
        Optional<Place> place = placeRepository.findById(placeReviewDTO.placeId());

        if (place.isEmpty() || place.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new ReviewPlaceException("El lugar no existe");
        }

        Jws<Claims> jws = jwtUtils.parseJwt(placeReviewDTO.token());
        String modId = jws.getPayload().get("id").toString();
        if (!userRepository.existsById(modId)) {
            throw new ReviewPlaceException("El moderador no existe");
        }

        Review review = Review.builder()
                .moderatorId(modId)
                .date(LocalDateTime.now().toString())
                .action(placeReviewDTO.action())
                .commentary(placeReviewDTO.commentary())
                .build();

        Place updatedPlace = place.get();
        updatedPlace.getReviews().add(review);
        updatedPlace.setStatus(placeReviewDTO.action());

        if (placeReviewDTO.action().equals(PlaceStatus.REJECTED)) {
            updatedPlace.setStatus(PlaceStatus.REJECTED.name());

            // TODO: validar 5 días hábiles para realizar cambios luego del rechazo
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime deadline = now.plusDays(5);

            updatedPlace.setDeletionDate(String.valueOf(deadline));

            // TODO: si sobrepasa los 5 días se eliminará automaticamente.
            if (now.isAfter(deadline)) {
                updatedPlace.setStatus(PlaceStatus.DELETED.name());
                updatedPlace.setDeletionDate(LocalDateTime.now().toString());
                placeRepository.save(updatedPlace);
                throw new ReviewPlaceException("El lugar ha sido eliminado automaticamente " +
                        "debido a que ha sobrepasado los 5 días  en estado rechazado");
            }
        } else if (placeReviewDTO.action().equals(PlaceStatus.APPROVED)) {
            updatedPlace.setStatus(PlaceStatus.APPROVED.name());
        }

        placeRepository.save(updatedPlace);

        User user = userRepository.findById(updatedPlace.getCreatedBy()).get();

        emailService.sendEmail(new EmailDTO("Nueva revisión en "+updatedPlace.getName(),
                "Su negocio ha sido revisado:  " +
                        "http://localhost:8080/api/place/review-place", user.getEmail()));
        // TODO: validar urls de los email
    }

    private void loadBannedNames() throws IOException {
        ClassPathResource resource = new ClassPathResource("banned_names.txt");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bannedNames.add(line.trim().toLowerCase());
            }
        }
    }

    private PlaceResponseDTO convertToPlaceResponseDTO(Place place) {
        return new PlaceResponseDTO(
                place.getId(),
                place.getName(),
                place.getDescription(),
                place.getLocation(),
                place.getImages(),
                place.getSchedules(),
                place.getPhones(),
                place.getCategories(),
                place.getReviews()
        );
    }

    private boolean isOpenNow(Place place) {

        List<Schedule> schedules = place.getSchedules();
        if (schedules == null || schedules.isEmpty()) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();

        for (Schedule schedule : schedules) {
            LocalTime openingTime = LocalTime.parse(schedule.getOpeningTime());
            LocalTime closingTime = LocalTime.parse(schedule.getClosingTime());

            if (currentTime.isAfter(ChronoLocalDateTime.from(openingTime)) && currentTime.isBefore(ChronoLocalDateTime.from(closingTime))) {
                return true;
            }
        }
        return false;
    }

}
