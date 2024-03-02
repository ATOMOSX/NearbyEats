package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.model.enums.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
}
