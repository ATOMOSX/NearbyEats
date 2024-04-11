package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{

    Boolean existsByName(String name);

    List<Place> findAllByCategoriesContaining(String category);

    List<Place> findAllByCreatedBy(String clientId);

    List<Place> findAllByStatus(String status);

    List<Place> findAllByLocation(Location location);

    List<Place> findAllByName(String name);
}
