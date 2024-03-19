package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.place.*;
import co.edu.uniquindio.proyecto.exceptions.place.*;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.PlaceRepo;
import co.edu.uniquindio.proyecto.services.interfaces.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

        Optional<Place> placeOptional = placeRepo.findById(deletePlaceDTO.idPlace());

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

        List<String> searchPlaces = new ArrayList<>();

        if (existName(searchPlaceDTO.name())) {
            throw new SearchPlaceException("El nombre que estas buscando no se encuentra en la base de datos");
        }

        if (existCategory(Collections.singletonList(searchPlaceDTO.category()))) {
            throw new SearchPlaceException("La categoria que estas buscando se encuentra vacia");
        }

        if (existLocation(searchPlaceDTO.location())) {
            throw new SearchPlaceException("La ubicación que estas tratando de buscar se encuentra vacia");
        }

        List<String> searchPlace = placeRepo.findByCriteria(searchPlaceDTO);

        for (String place : searchPlace) {

            searchPlace.add(searchPlaceDTO.name());
        }

        return searchPlaces;

    }

    @Override
    public List<ItemPlaceStatusDTO> filterStatusPlace(FilterStatusPlaceDTO filterStatusPlaceDTO) throws FilterStatusPlaceException {

        List<Place> places = placeRepo.findByStatus(Status.ACTIVE);

        return places.stream().map(
                p -> new ItemPlaceStatusDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription())).toList();
    }

    @Override
    public List<ItemPlaceOwnerDTO> listPlaceOwner(ListPlaceOwnerDTO listPlaceOwnerDTO) throws ListPlaceOwnerException {

        List<Place> places = placeRepo.findByOwner(listPlaceOwnerDTO.idClient());

        return places.stream().map(
                p -> new ItemPlaceOwnerDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription())).toList();
    }

    public boolean existName(String name){
        return placeRepo.findByNamePlace(name).isPresent();
    }

    public boolean existLocation(Ubication location){
        return !placeRepo.findByLocation(location).isEmpty();
    }

    public boolean existCategory(List<Category> categories){
        return !placeRepo.findByCategories(categories).isEmpty();
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
