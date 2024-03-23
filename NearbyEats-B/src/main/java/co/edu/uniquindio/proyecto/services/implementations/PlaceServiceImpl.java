package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.place.*;
import co.edu.uniquindio.proyecto.exceptions.place.*;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.repository.PlaceRepo;
import co.edu.uniquindio.proyecto.services.interfaces.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepo placeRepo;
    private final ClientRepo clientRepo;
    private Set<String> forbiddenName;

    @Override
    public void createPlace(CreatePlaceDTO createPlaceDTO) throws CreatePlaceException {
        if (existName(createPlaceDTO.name())) {
            throw new CreatePlaceException("El nombre que deseas usar no se encuentra disponible, por favor intenta " +
                    "ingresar uno nuevo");
        }

        if (isForbiddenName(createPlaceDTO.name())) {
            throw new CreatePlaceException("El nombre que deseas usar no está permitido en nuestra plataforma");
        }

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

        client.getCreatedPlaces().add(place);
        placeRepo.save(place);
        clientRepo.save(client);
    }

    @Override
    public void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException {
        Optional<Place> placeOptional = placeRepo.findById(deletePlaceDTO.idPlace());

        if (placeOptional.isEmpty()) {
            throw new DeletePlaceException("El id del lugar esta vacío, no puede ser eliminado");
        }

        Optional<Client> optionalClient = clientRepo.findById(deletePlaceDTO.clientId());
        Client client = optionalClient.get();

        Place place = placeOptional.get();
        place.setStatus(Status.INACTIVE);
        place.setDeletionDate(LocalDateTime.now());
        int placeIndex = client.getCreatedPlaces().indexOf(place);
        client.getCreatedPlaces().set(placeIndex, null);

        placeRepo.save(place);
        clientRepo.save(client);
    }

    @Override
    public void updatePlace(UpdatePlaceDto updatePlaceDto) throws UpdatePlaceException {
        Optional<Place> placeOptional = placeRepo.findById(updatePlaceDto.id());

        if (placeOptional.isEmpty()) {
            throw new UpdatePlaceException("El id del negocio no puede estar vacío para poder actualizarlo");
        }

        Place place = placeOptional.get();
        place.setName(updatePlaceDto.name());
        place.setDescription(updatePlaceDto.description());
        place.setLocation(updatePlaceDto.location());
        place.setPictures(updatePlaceDto.pictures());
        place.setSchedule(updatePlaceDto.schedule());
        place.setPhones(updatePlaceDto.phones());
        place.setCategories(updatePlaceDto.categories());

        placeRepo.save(place);
    }

    @Override
    public List<ListPlaceDTO> listPlaceByCategory(Category category) throws SearchPlaceException {
        if (existCategory(category))
            throw new SearchPlaceException("La categoría que estás buscando se encuentra vacía");

        return placeRepo.findByCategory(category);
    }

    @Override
    public List<ListPlaceDTO> listPlaceByLocation(Ubication location) throws SearchPlaceException {
        if (existLocation(location))
            throw new SearchPlaceException("La ubicación que estás tratando de buscar se encuentra vacía");

        return placeRepo.findByLocation(location);
    }

    @Override
    public List<ListPlaceDTO> listPlaceByName(String name) throws SearchPlaceException {
        if (existName(name))
            throw new SearchPlaceException("El nombre que estás buscando no se encuentra en la base de datos");

        return placeRepo.findByName(name);
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
    public List<ItemPlaceOwnerDTO> listPlaceOwner(String clientId) throws ListPlaceOwnerException {
        Optional<Client> clientOptional = clientRepo.findById(clientId);

        if(clientOptional.isEmpty())
            throw new ListPlaceOwnerException("El cliente no existe");

        return clientRepo.findPlacesById(clientId);
    }

    @Override
    public GetPlaceDTO getPlace(String id) throws GetPlaceException {
        Optional<Place> placeOptional = placeRepo.findById(id);

        if (placeOptional.isEmpty())
            throw new GetPlaceException("El id no puede ser vacío");

        Place place = placeOptional.get();

        if (place.getStatus() == Status.INACTIVE){
            throw new GetPlaceException("No se puede obtener un lugar inactivo");
        }

        return  new GetPlaceDTO(
                id,
                place.getName(),
                place.getDescription(),
                place.getLocation(),
                place.getPictures(),
                place.getSchedule(),
                place.getPhones(),
                place.getCategories(),
                place.getRevisionsHistory()
        );
    }

    public boolean existName(String name){
        return placeRepo.findByName(name).isEmpty();
    }

    public boolean existLocation(Ubication location){
        return placeRepo.findByLocation(location).isEmpty();
    }

    public boolean existCategory(Category category){
        return placeRepo.findByCategory(category).isEmpty();
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
