package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.place.*;
import co.edu.uniquindio.proyecto.exceptions.place.*;
import co.edu.uniquindio.proyecto.model.documents.Client;

import java.util.List;

public interface PlaceService {

    void createPlace(CreatePlaceDTO createPlaceDTO) throws CreatePlaceException;

    void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException;

    void updatePlace(UpdatePlaceDto updatePlaceDto) throws UpdatePlaceException;

    List<String> searchPlace(SearchPlaceDTO searchPlaceDTO) throws SearchPlaceException;

    List<String> filterStatusPlace(FilterStatusPlaceDTO filterStatusPlaceDTO) throws FilterStatusPlaceException;

    List<String> listPlaceOwner(ListPlaceOwnerDTO listPlaceOwnerDTO) throws ListPlaceOwnerException;

}
