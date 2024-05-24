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
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{

    Boolean existsByName(String name);


    List<Place> findAllByCategoriesContainingIgnoreCase(String category);

    List<Place> findAllByCreatedBy(String clientId);

    List<Place> findAllByStatus(String status);


    List<Place> findAllByLocation(String location);


    List<Place> findAllByNameContainingIgnoreCase(String name);

    List<Place> findAllByStatusAndCreatedBy(String status, String createdBy);

    @Aggregation(pipeline = {
            "{$unwind: '$reviews'}",
            "{$match: { status: ?0, 'reviews.moderatorId': ?1 } }",
            "{$lookup: { from: 'users', localField: 'reviews.moderatorId', foreignField: '_id', as: 'moderator' } }",
            "{$unwind: '$moderator'}",
            "{$group: { _id: '$_id', name: { $first: '$name' }, description: { $first: '$description' }, location: { $first: '$location' }, pictures: { $first: '$pictures' }, schedules: { $first: '$schedules' }, phones: { $first: '$phones' }, categories: { $first: '$categories' }, reviews: { $push: '$reviews' } } }",
            "{$project: { _id: 1, name: 1, description: 1, location: 1, pictures: 1, schedules: 1, phones: 1, categories: 1, reviews: 1 } }"
    })
    List<Place> getPlacesByStatusByModerator(String status, String moderatorID);

    List<Place> findAllByCategoriesContaining(List<PlaceCategory> categories);

    Place findByName(String name);
}
