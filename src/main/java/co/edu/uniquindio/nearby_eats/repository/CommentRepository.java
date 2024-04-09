package co.edu.uniquindio.nearby_eats.repository;

import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>{

    Boolean existsByUserAndPlace(String clientId, String placeId);

    List<Comment> findAllByPlace(String placeId);

    @Aggregation(pipeline = {
            "{ $match: { 'place': ?0 } }",
            "{ $group: { _id: '$place', averageRating: { $avg: '$rating' } } }"
    })
    Float findAverageRatingByPlace(String placeId);

    /*

    @Aggregation({"{$match: {placeId: ?0}}", "{$lookup: {from: 'place', localField: 'placeId', foreignField: '_id', as: 'place'}}", "{$unwind: '$place'}", "{$group: {_id: '$place.name', average: {$avg: '$score'} }}" })
    float calculateAverageScore(String placeId);
     */
}
