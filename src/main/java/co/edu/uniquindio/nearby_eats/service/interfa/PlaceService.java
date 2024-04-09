package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.place.PlaceCreateDTO;
import co.edu.uniquindio.nearby_eats.dto.request.review.PlaceReviewDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.place.CreatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.DeletePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.GetPlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.place.UpdatePlaceException;
import co.edu.uniquindio.nearby_eats.exceptions.review.ReviewPlaceException;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;

import java.util.List;

public interface PlaceService {

    void createPlace(PlaceCreateDTO placeCreateDTO) throws CreatePlaceException;

    void updatePlace(String placeId, PlaceCreateDTO placeCreateDTO) throws UpdatePlaceException;

    void deletePlace(String placeId) throws DeletePlaceException;

    PlaceResponseDTO getPlace(String placeId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByCategory(String category);

    List<PlaceResponseDTO> getPlacesByStatus(String status);

    List<PlaceResponseDTO> getPlacesByClientId(String clientId) throws GetPlaceException;

    List<PlaceResponseDTO> getPlacesByLocation(Location location);

    // TODO: Implementar el método para obtener los lugares más cercanos a una ubicación dada y un radio de búsqueda

    void reviewPlace(PlaceReviewDTO placeReviewDTO) throws ReviewPlaceException;

}
