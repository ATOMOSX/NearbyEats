package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.place.*;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.*;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PlaceService {

    Place createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException;

    Place updatePlace(UpdatePlaceDTO updatePlaceDTO) throws UpdatePlaceException;

    Place deletePlace(DeletePlaceDTO deletePlaceDTO) throws DeletePlaceException;

    PlaceResponseDTO getPlace(String placeId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByCategory(GetPlacesByCategoryDTO getPlacesByCategoryDTO, String token) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByStatus(GetPlacesByStatusByClientDTO getPlacesByStatusByClientDTO, String token) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByClientId(String clientId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByLocation(GetPlacesByLocation getPlacesByLocation, String token) throws GetPlaceException;

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    List<PlaceResponseDTO> getPlacesByModerator(GetPlacesByModeratorDTO getPlacesByModeratorDTO) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByName(GetPlacesByNameDTO getPlacesByNameDTO, String token) throws GetPlaceException;

    Place saveFavoritePlace(FavoritePlaceDTO favoritePlaceDTO, String token) throws FavoritePlaceException;

    Place deleteFavoritePlace(FavoritePlaceDTO deleteFavoritePlaceDTO, String token) throws FavoritePlaceException;

    void reviewPlace(PlaceReviewDTO placeReviewDTO, String token) throws ReviewPlaceException, MessagingException, EmailServiceException;

    List<String> getPlaceStatus();
}
