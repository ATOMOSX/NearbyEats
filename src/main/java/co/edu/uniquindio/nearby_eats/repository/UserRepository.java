package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.model.docs.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id, Boolean isActive);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmail(String email, Boolean isActive);
    Optional<User> findByNickname (String nickname);
    Optional<User> findByNickname(String nickname, Boolean isActive);
    List<User> findAllByIsActive(Boolean isActive);
}
