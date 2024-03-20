package co.edu.uniquindio.proyecto.repository;

import co.edu.uniquindio.proyecto.dto.comment.ItemCommentDTO;
import co.edu.uniquindio.proyecto.model.documents.Commentary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentaryRepo extends MongoRepository<Commentary, String> {

    @Aggregation({"{$match: {placeId: ?0, status:  'ACTIVE'}}", "{$lookup: {from: 'clientes', localField: 'nickName', foreignField: 'nickname', as: 'client'}}", "{$unwind: '$client'}", "{$project: { comment: '$commentary', firstName: '$client.firstName',  lastName: '$client.lastName', photo: '$client.profilePhoto', date: '$date', score: '$score', answer: '$answer' }}" })
    List<ItemCommentDTO> listComments(String placeId);
}
