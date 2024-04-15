package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.dto.response.place.PlaceResponseDTO;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceCategory;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{

    Boolean existsByName(String name);

    List<Place> findAllByCategoriesContaining(PlaceCategory category);

    List<Place> findAllByCreatedBy(String clientId);

    List<Place> findAllByStatus(String status);

    List<Place> findAllByLocation(Location location);

    List<Place> findAllByName(String name);

    List<Place> findAllByStatusAndCreatedBy(PlaceStatus status, String clientId);

    @Aggregation({"{$unwind: '$reviews'}",
            "{$lookup: { from: 'users', localField: 'reviews.moderatorId', foreignField: '_id', as: 'moderator' } }",
            "{ $match: { status: ?0, moderator: { $ne: [] }, 'moderator._id': ?1 } }",
            "{ $project: { _id: 1, name: 1, description: 1, location: 1, pictures: 1, schedule: 1, phones: 1, categories: 1, revisionsHistory: 1 } }"})
    List<PlaceResponseDTO> getPlacesByStatusByModerator(String status, String moderatorID);

    List<Place> findAllByCategoriesContaining(List<PlaceCategory> categories);

    Place findByName(String name);
}
