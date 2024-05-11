package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.*;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import jakarta.mail.MessagingException;

import java.util.List;

public interface CommentService {

    Comment createComment(CommentDTO commentDTO, String token) throws CreateCommentException, MessagingException, EmailServiceException;

    Comment answerComment(ReplyDTO replyDTO, String token) throws AnswerCommentException, MessagingException, EmailServiceException;

    String deleteComment(DeleteCommentDTO deleteCommentDTO, String token) throws DeleteCommentException;

    List<CommentResponseDTO> getCommentsByPlace(String placeId) throws ListCommentsException;

    Float getAverageScoreByPlace(String placeId) throws GetAverageScoreCommentException;

}
