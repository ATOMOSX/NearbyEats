package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Comentary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentaryRepo extends MongoRepository<Comentary, String> {

    List<Comentary> findByIdPlace(String idPlace);
}
