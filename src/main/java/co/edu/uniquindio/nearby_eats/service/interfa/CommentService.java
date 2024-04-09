package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.CommentException;

import java.util.List;

public interface CommentService {

    void createComment(CommentDTO commentDTO) throws CommentException;

    void answerComment(ReplyDTO replyDTO, String commentId) throws CommentException;

    void deleteComment(String commentId) throws CommentException;

    List<CommentResponseDTO> getCommentsByPlace(String placeId) throws CommentException;

    Float getAverageScoreByPlace(String placeId) throws CommentException;

}
