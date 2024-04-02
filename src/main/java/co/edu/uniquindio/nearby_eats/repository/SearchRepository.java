package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.model.docs.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<Search, String> {
}
