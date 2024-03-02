package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.model.documents.Revision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepo extends MongoRepository<Revision, String> {
}
