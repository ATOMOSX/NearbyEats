package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.place.SearchPlaceDTO;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepo extends MongoRepository<Place, String> {
    Optional<Place> findByNamePlace (String name);
    Optional<Place> findById (String id);
    List<Place> findByCategories(List<Category> category);
    List<Place> findByLocation(Ubication location);
    List<String> findByCriteria(SearchPlaceDTO searchPlaceDTO);
    List<Place> findByStatus(Status status);
    List<Place> findByOwner(String owner);
}
