package co.edu.uniquindio.nearby_eats.controlers;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.AnswerCommentException;
import co.edu.uniquindio.nearby_eats.exceptions.comment.DeleteCommentException;
import co.edu.uniquindio.nearby_eats.exceptions.comment.GetAverageScoreCommentException;
import co.edu.uniquindio.nearby_eats.exceptions.comment.ListCommentsException;
import co.edu.uniquindio.nearby_eats.service.interfa.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/answer-comment")
    public ResponseEntity<MessageDTO<String>> answerComment(@Valid @RequestBody ReplyDTO replyDTO) throws AnswerCommentException{
        commentService.answerComment(replyDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "answer commentary is successful"));
    }

    @PostMapping("/delete-comment")
    public ResponseEntity<MessageDTO<String>> deleteComment(@Valid @RequestBody DeleteCommentDTO deleteCommentDTO) throws DeleteCommentException{
        commentService.deleteComment(deleteCommentDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "comment is delete correctly"));
    }

    @PostMapping("/get-comments-by-place/{placeId}")
    public ResponseEntity<MessageDTO<List<CommentResponseDTO>>> getCommentsByPlace(@PathVariable String placeId) throws ListCommentsException{
        commentService.getCommentsByPlace(placeId);
        return ResponseEntity.ok().body(new MessageDTO<>(false, commentService.getCommentsByPlace(placeId)));
    }

    @PostMapping("/get-average-score-by-place/{placeId}")
    public ResponseEntity<MessageDTO<Float>> getAverageScoreByPlace(@PathVariable String placeId) throws GetAverageScoreCommentException{
        commentService.getAverageScoreByPlace(placeId);
        return ResponseEntity.ok().body(new MessageDTO<>(false, commentService.getAverageScoreByPlace(placeId)));
    }

}