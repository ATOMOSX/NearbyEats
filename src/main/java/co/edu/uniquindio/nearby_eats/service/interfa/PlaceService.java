package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;

import java.util.List;

public interface PlaceService {

    void createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException;

    void updatePlace(UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException;

    void deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException;

    PlaceResponseDTO getPlace(String placeId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByCategory(String category) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByStatus(String status) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByClientId(String clientId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByLocation(Location location) throws GetPlaceException;

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    List<PlaceResponseDTO> getPlacesByName(String name) throws GetPlaceException;

    void saveFavoritePlace(FavoritePlaceDTO favoritePlaceDTO) throws FavoritePlaceExcpetion;

    void deleteFavoritePlace(FavoritePlaceDTO deleteFavoritePlaceDTO) throws FavoritePlaceExcpetion;

    void reviewPlace(PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException;

}
