package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Moderator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeratorRepo extends MongoRepository<Moderator, String> {
    Optional<Moderator> findByNickname(String nickname);
}
