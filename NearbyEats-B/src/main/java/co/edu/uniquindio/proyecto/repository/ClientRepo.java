package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.place.ItemPlaceOwnerDTO;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends MongoRepository<Client, String> {

    Optional<Client> findByNickname(String nickname);

    Optional<Client> findByEmail(String email);

    List<Client> findByStatus(Status status);

    @Query(value = "{ '_id' : ?0 }", fields = "{ '_id' : 0, 'createdPlaces' : 1 }")
    List<ItemPlaceOwnerDTO> findPlacesById(String id);
}
