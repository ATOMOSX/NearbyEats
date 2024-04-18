package co.edu.uniquindio.nearby_eats.controllers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.*;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import co.edu.uniquindio.nearby_eats.service.interfa.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create-comment")
    public ResponseEntity<MessageDTO<Comment>> createComment(@Valid @RequestBody CommentDTO commentDTO) throws MessagingException, EmailServiceException, CreateCommentException {
        Comment comment = commentService.createComment(commentDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, comment));
    }
    @PostMapping("/answer-comment")
    public ResponseEntity<MessageDTO<Comment>> answerComment(@Valid @RequestBody ReplyDTO replyDTO) throws AnswerCommentException, MessagingException, EmailServiceException {
        Comment comment = commentService.answerComment(replyDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, comment));
    }

    @DeleteMapping("/delete-comment")
    public ResponseEntity<MessageDTO<String>> deleteComment(@Valid @RequestBody DeleteCommentDTO deleteCommentDTO) throws DeleteCommentException{
        commentService.deleteComment(deleteCommentDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "comment is delete correctly"));
    }

    @GetMapping("/get-comments-by-place/{placeId}")
    public ResponseEntity<MessageDTO<List<CommentResponseDTO>>> getCommentsByPlace(@PathVariable String placeId) throws ListCommentsException{
        return ResponseEntity.ok().body(new MessageDTO<>(false, commentService.getCommentsByPlace(placeId)));
    }

    @GetMapping("/get-average-score-by-place/{placeId}")
    public ResponseEntity<MessageDTO<Float>> getAverageScoreByPlace(@PathVariable String placeId) throws GetAverageScoreCommentException{
        return ResponseEntity.ok().body(new MessageDTO<>(false, commentService.getAverageScoreByPlace(placeId)));
    }

}
