package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.SaveSearchDTO;
import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;

import java.util.List;

public interface SearchService {

    // Agregar una búsqueda
    void saveSearch(SaveSearchDTO saveSearchDTO);

    //TODO: recomendar lugares en función de búsquedas del usuario
    List<PlaceResponseDTO> recommendPlaces(String userId) throws Exception;

}
