package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.model.docs.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByIdAndIsActive(String id, Boolean isActive);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndIsActive(String email, Boolean isActive);
    Boolean existsByNicknameAndIsActive(String nickname, Boolean isActive);
    Optional<User> findByNicknameAndIsActive(String nickname, Boolean isActive);

    List<User> findAllByIsActive(Boolean isActive);
}
