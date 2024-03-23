package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.place.*;
import co.edu.uniquindio.proyecto.exceptions.place.*;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;

import java.util.List;

public interface PlaceService {

    void createPlace(CreatePlaceDTO createPlaceDTO) throws CreatePlaceException;

    void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException;

    void updatePlace(UpdatePlaceDto updatePlaceDto) throws UpdatePlaceException;

    List<ListPlaceDTO> listPlaceByCategory(Category category) throws SearchPlaceException;

    List<ListPlaceDTO> listPlaceByLocation(Ubication location) throws SearchPlaceException;

    List<ListPlaceDTO> listPlaceByName(String name) throws SearchPlaceException;

    List<ItemPlaceStatusDTO> filterStatusPlace(FilterStatusPlaceDTO filterStatusPlaceDTO) throws FilterStatusPlaceException;

    List<ItemPlaceOwnerDTO> listPlaceOwner(String clientId) throws ListPlaceOwnerException;

    GetPlaceDTO getPlace(String id) throws GetPlaceException;
}
