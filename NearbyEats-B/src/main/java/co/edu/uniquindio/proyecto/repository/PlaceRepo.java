package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.model.documents.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepo extends MongoRepository<Place, String> {
}
