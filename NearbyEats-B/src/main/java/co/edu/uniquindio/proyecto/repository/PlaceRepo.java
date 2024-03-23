package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.place.ListPlaceDTO;
import co.edu.uniquindio.proyecto.model.documents.Place;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepo extends MongoRepository<Place, String> {

    Optional<Place> findById (String id);

    @Query(value = "{ 'status' : 'ACTIVE' }")
    List<ListPlaceDTO> findByCategory(Category category);

    @Query(value = "{ 'status' : 'ACTIVE' }")
    List<ListPlaceDTO> findByLocation(Ubication location);

    List<Place> findByStatus(Status status);

    @Query(value = "{ 'status' : 'ACTIVE' }")
    List<ListPlaceDTO> findByName(String name);
}
