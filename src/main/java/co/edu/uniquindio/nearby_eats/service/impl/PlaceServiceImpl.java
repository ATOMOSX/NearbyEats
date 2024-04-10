package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.request.place.DeletePlaceDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.PlaceCreateDTO;
import co.edu.uniquindio.nearby_eats.dto.request.place.UpdatePlaceDTO;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.CreatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.DeletePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.GetPlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.UpdatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final Set<String> bannedNames = new HashSet<>();

    public PlaceServiceImpl(PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;

        try {
            loadBannedNames();
        } catch (IOException e) {
            log.error("Error loading banned names", e);
        }
    }

    @Override
    public void createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException {

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
    }

    private boolean isBannedName(String name) {
        return bannedNames.contains(name.trim().toLowerCase());
    }

    @Override
    public void updatePlace(UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException {
        Optional<Place> place = placeRepository.findById(updatePlaceDTO.placeId());

        if (place.isEmpty() || place.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new UpdatePlaceException("El lugar no existe");
        }

        if (isBannedName(updatePlaceDTO.name())) {
            throw new UpdatePlaceException("El nombre del lugar no es permitido");
        }

        Place updatedPlace = place.get();
        updatedPlace.setName(updatePlaceDTO.name());
        updatedPlace.setDescription(updatePlaceDTO.description());
        updatedPlace.setLocation(updatePlaceDTO.location());
        updatedPlace.setImages(updatePlaceDTO.images());
        updatedPlace.setSchedules(updatePlaceDTO.schedule());
        updatedPlace.setPhones(updatePlaceDTO.phones());
        updatedPlace.setCategories(updatePlaceDTO.categories());

        placeRepository.save(updatedPlace);
    }

    @Override
    public void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {
        Optional<Place> place = placeRepository.findById(deletePlaceDTO.placeId());

        if (place.isEmpty() || place.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new DeletePlaceException("El lugar no existe");
        }

        Place deletedPlace = place.get();
        deletedPlace.setStatus(PlaceStatus.DELETED.name());
        deletedPlace.setDeletionDate(LocalDateTime.now().toString());

        Optional<User> optionalUser = userRepository.findById(deletePlaceDTO.clientId());
        User user = optionalUser.get();

        int placeIndex = user.getCreatedPlaces().indexOf(deletedPlace);
        user.getCreatedPlaces().set(placeIndex, null);

        placeRepository.save(deletedPlace);
        userRepository.save(user);
    }

    @Override
    public PlaceResponseDTO getPlace(String placeId) throws GetPlaceException {
        Optional<Place> place = placeRepository.findById(placeId);

        if (place.isEmpty() || place.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new GetPlaceException("El lugar no existe");
        }

        return convertToPlaceResponseDTO(place.get());
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

    @Override
    public List<PlaceResponseDTO> getPlacesByCategory(String category) {
        List<Place> places = placeRepository.findAllByCategoriesContaining(category);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    @Override
    public List<PlaceResponseDTO> getPlacesByStatus(String status) {
        List<Place> places = placeRepository.findAllByStatus(status);
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

    @Override
    public List<PlaceResponseDTO> getPlacesByLocation(Location location) {
        List<Place> places = placeRepository.findAllByLocation(location);
        return places.stream().map(this::convertToPlaceResponseDTO).toList();
    }

    @Override
    public void reviewPlace(PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException {
        Optional<Place> place = placeRepository.findById(placeReviewDTO.placeId());

        if (place.isEmpty() || place.get().getStatus().equals(PlaceStatus.DELETED.name())) {
            throw new ReviewPlaceException("El lugar no existe");
        }

        if (!userRepository.existsById(placeReviewDTO.moderatorId())) {
            throw new ReviewPlaceException("El moderador no existe");
        }

        Review review = Review.builder()
                .moderatorId(placeReviewDTO.moderatorId())
                .date(LocalDateTime.now().toString())
                .action(placeReviewDTO.action().name())
                .commentary(placeReviewDTO.commentary())
                .build();

        Place updatedPlace = place.get();
        updatedPlace.getReviews().add(review);

        if (placeReviewDTO.action().equals(PlaceStatus.APPROVED)) {
            updatedPlace.setStatus(PlaceStatus.APPROVED.name());
        } else if (placeReviewDTO.action().equals(PlaceStatus.REJECTED)) {
            updatedPlace.setStatus(PlaceStatus.REJECTED.name());
        }

        placeRepository.save(updatedPlace);
    }

    // TODO: validar 5 días hábiles para realizar cambios luego del rechazo

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

}
