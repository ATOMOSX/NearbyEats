package co.edu.uniquindio.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepo extends MongoRepository<Moderator, String> {
}
