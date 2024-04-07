package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.ReplyResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.CommentException;
import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.subdocs.Reply;
import co.edu.uniquindio.nearby_eats.repository.CommentRepository;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PlaceRepository placeRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(CommentDTO commentDTO) throws CommentException {

        // Verificar si el lugar existe
        if (!placeRepository.existsById(commentDTO.placeId())) {
            throw new CommentException("El lugar no existe");
        }

        // Verificar si el usuario existe
        if (!userRepository.existsById(commentDTO.clientId())) {
            throw new CommentException("El usuario no existe");
        }

        // Verificar si el usuario ya ha comentado en el lugar
        if (commentRepository.existsByUserAndPlace(commentDTO.clientId(), commentDTO.placeId())) {
            throw new CommentException("El usuario ya ha comentado en este lugar");
        }

        // Verificar si la calificación está entre 1 y 5
        if (commentDTO.score() < 1 || commentDTO.score() > 5) {
            throw new CommentException("La calificación debe estar entre 1 y 5");
        }

        Comment comment = new Comment();
        comment.setPlace(commentDTO.placeId());
        comment.setUser(commentDTO.clientId());
        comment.setText(commentDTO.comment());
        comment.setRating(commentDTO.score());
        comment.setDate(LocalDateTime.now());

        commentRepository.save(comment);
    }

    @Override
    public void answerComment(ReplyDTO replyDTO, String commentId) throws CommentException {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new CommentException("El comentario no existe");
        }

        Optional<Place> place = placeRepository.findById(commentOptional.get().getPlace());
        if (place.isEmpty()) {
            throw new CommentException("El lugar no existe");
        }

        // Verificar si el usuario es el dueño del lugar
        if (!place.get().getCreatedBy().equals(replyDTO.respondedBy())) {
            throw new CommentException("El usuario no es el dueño del lugar");
        }

        Comment comment = commentOptional.get();

        Reply reply = new Reply(
                replyDTO.text(),
                LocalDateTime.now(),
                replyDTO.respondedBy()
        );

        comment.setReply(reply);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) throws CommentException {
        if (!commentRepository.existsById(commentId)) {
            throw new CommentException("El comentario no existe");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentResponseDTO> getCommentsByPlace(String placeId) throws CommentException {
        if (!placeRepository.existsById(placeId)) {
            throw new CommentException("El lugar no existe");
        }

        List<Comment> comments = commentRepository.findAllByPlace(placeId);

        return comments.stream()
                .map(this::mapCommentToCommentResponseDTO)
                .toList();
    }

    private CommentResponseDTO mapCommentToCommentResponseDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getDate().toString(),
                comment.getUser(),
                comment.getText(),
                comment.getRating(),
                comment.getReply() != null ? new ReplyResponseDTO(
                        comment.getReply().getDate().toString(),
                        comment.getReply().getRespondedBy(),
                        comment.getReply().getText()
                ) : null
        );
    }

    @Override
    public Float getAverageScoreByPlace(String placeId) throws CommentException {
        if (!placeRepository.existsById(placeId)) {
            throw new CommentException("El lugar no existe");
        }
        Float average = commentRepository.findAverageRatingByPlace(placeId);
        return average != null ? average : 0.0f;
    }
}
