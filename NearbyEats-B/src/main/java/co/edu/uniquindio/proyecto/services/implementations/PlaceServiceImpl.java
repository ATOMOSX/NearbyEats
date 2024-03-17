package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.place.*;
import co.edu.uniquindio.proyecto.exceptions.place.*;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.PlaceRepo;
import co.edu.uniquindio.proyecto.services.interfaces.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepo placeRepo;
    private Set<String> forbiddenName;

    @Override
    public void createPlace(CreatePlaceDTO createPlaceDTO) throws CreatePlaceException {

        if (existName(createPlaceDTO.name())) {
            throw new CreatePlaceException("El nombre que deseas usar ya se encuentra disponible, por favor verificalo bien");
        }

        if (isForbiddenName(createPlaceDTO.name())) {
            throw new CreatePlaceException("El nombre que deseas usar no esta permitido en nuestra plataforma");
        }

        Place place = Place.builder()
                .name(createPlaceDTO.name())
                .description(createPlaceDTO.description())
                .location(createPlaceDTO.location())
                .pictures(createPlaceDTO.pictures())
                .schedule(createPlaceDTO.schedule())
                .phones(createPlaceDTO.phones())
                .categories(createPlaceDTO.categories())
                .build();


        Place registerPlace = placeRepo.save(place);
    }

    @Override
    public void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {

        Optional<Place> placeOptional = placeRepo.findById(deletePlaceDTO.id());

        if (placeOptional.isEmpty()) {
            throw new DeletePlaceException("El id del lugar esta vacio, no puede ser eliminado");
        }

        Place place = placeOptional.get();
        place.setStatus(Status.INACTIVE);

        placeRepo.save(place);
    }

    @Override
    public void updatePlace(UpdatePlaceDto updatePlaceDto) throws UpdatePlaceException {

        Optional<Place> placeOptional = placeRepo.findById(updatePlaceDto.id());

        if (placeOptional.isEmpty()) {
            throw new UpdatePlaceException("El id del negocio no puede estar vacio para poder actualizarlo");
        }

        Place place = placeOptional.get();
        place.setName(updatePlaceDto.name());
        place.setDescription(updatePlaceDto.description());
        place.setLocation(updatePlaceDto.location());
        place.setPictures(updatePlaceDto.pictures());
        place.setSchedule(updatePlaceDto.schedule());
        place.setPhones(updatePlaceDto.phones());
        place.setCategories(updatePlaceDto.categories());
    }

    @Override
    public List<String> searchPlace(SearchPlaceDTO searchPlaceDTO) throws SearchPlaceException {
        return null;
    }

    @Override
    public List<String> filterStatusPlace(FilterStatusPlaceDTO filterStatusPlaceDTO) throws FilterStatusPlaceException {
        return null;
    }

    @Override
    public List<String> listPlaceOwner(ListPlaceOwnerDTO listPlaceOwnerDTO) throws ListPlaceOwnerException {
        return null;
    }

    public boolean existName(String name){
        return placeRepo.findByNamePlace(name).isPresent();
    }

    private void uploadForbiddenName() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("nombres_prohibidos.txt"))) {
            String name;
            while ((name = bufferedReader.readLine()) != null) {
                forbiddenName.add(name.trim());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isForbiddenName (String name) {
        return forbiddenName.contains(name);
    }
}
