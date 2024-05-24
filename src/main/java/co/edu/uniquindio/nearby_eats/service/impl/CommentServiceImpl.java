package co.edu.uniquindio.nearby_eats.service.impl;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.CommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.DeleteCommentDTO;
import co.edu.uniquindio.nearby_eats.dto.request.comment.ReplyDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.CommentResponseDTO;
import co.edu.uniquindio.nearby_eats.dto.response.comment.ReplyResponseDTO;
import co.edu.uniquindio.nearby_eats.exceptions.comment.*;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import co.edu.uniquindio.nearby_eats.model.docs.Comment;
import co.edu.uniquindio.nearby_eats.model.docs.Place;
import co.edu.uniquindio.nearby_eats.model.docs.User;
import co.edu.uniquindio.nearby_eats.model.enums.PlaceStatus;
import co.edu.uniquindio.nearby_eats.model.subdocs.Reply;
import co.edu.uniquindio.nearby_eats.repository.CommentRepository;
import co.edu.uniquindio.nearby_eats.repository.PlaceRepository;
import co.edu.uniquindio.nearby_eats.repository.UserRepository;
import co.edu.uniquindio.nearby_eats.service.interfa.CommentService;
import co.edu.uniquindio.nearby_eats.service.interfa.EmailService;
import co.edu.uniquindio.nearby_eats.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.mail.MessagingException;
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
    private final EmailService emailService;
    private final JwtUtils jwtUtils;

    public CommentServiceImpl(CommentRepository commentRepository, PlaceRepository placeRepository, UserRepository userRepository, EmailService emailService, JwtUtils jwtUtils) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Comment createComment(CommentDTO commentDTO, String token) throws CreateCommentException, MessagingException, EmailServiceException, GetAverageScoreCommentException {
        Jws<Claims> jws = jwtUtils.parseJwt(token);
        String userId = jws.getPayload().get("id").toString();
        System.out.println(commentDTO.placeId());

        Optional<Place> placeOptional = placeRepository.findById(commentDTO.placeId());
        if (placeOptional.isEmpty()) {
            throw new CreateCommentException("El lugar no existe");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new CreateCommentException("El usuario no existe");
        }

        // Verificar que el usuario no sea el dueño del lugar
        if (placeOptional.get().getCreatedBy().equals(userId)) {
            throw new CreateCommentException("El usuario no puede comentar en su propio lugar");
        }

        // Verificar si el usuario ya ha comentado en el lugar
        if (commentRepository.existsByUserAndPlace(userId, commentDTO.placeId())) {
            throw new CreateCommentException("El usuario ya ha comentado en este lugar");
        }

        // Verificar si la calificación está entre 1 y 5
        if (commentDTO.score() < 1 || commentDTO.score() > 5) {
            throw new CreateCommentException("La calificación debe estar entre 1 y 5");
        }

        Comment comment = Comment.builder()
                .place(commentDTO.placeId())
                .user(userId)
                .text(commentDTO.comment())
                .rating(commentDTO.score())
                .date(LocalDateTime.now().toString())
                .build();

        Comment comment1 = commentRepository.save(comment);
        float score = getAverageScoreByPlace(commentDTO.placeId());
        Place place = placeOptional.get();
        place.setScore(score);
        placeRepository.save(place);

        Optional<User> ownerOptional = userRepository.findById(placeOptional.get().getCreatedBy());
        User owner = ownerOptional.get();
        emailService.sendEmail(new EmailDTO("Nuevo comentario en "+placeOptional.get().getName(),
                "Para responder el comentario, ingrese al siguiente enlace:  http://localhost:8080/api/comment/answer-comment", owner.getEmail()));
        return comment1;
    }

    @Override
    public Comment answerComment(ReplyDTO replyDTO, String token) throws AnswerCommentException, MessagingException, EmailServiceException {
        Jws<Claims> jws = jwtUtils.parseJwt(token);
        String userId = jws.getPayload().get("id").toString();

        Optional<Comment> commentOptional = commentRepository.findById(replyDTO.commentId());
        if (commentOptional.isEmpty()) {
            throw new AnswerCommentException("El comentario no existe");
        }

        Optional<Place> place = placeRepository.findById(commentOptional.get().getPlace());
        if (place.isEmpty()) {
            throw new AnswerCommentException("El lugar no existe");
        }

        // Verificar si el usuario es el dueño del lugar
        if (!place.get().getCreatedBy().equals(userId)) {
            throw new AnswerCommentException("El usuario no es el dueño del lugar");
        }

        Comment comment = commentOptional.get();

        Reply reply = Reply.builder()
                .text(replyDTO.text())
                .date(LocalDateTime.now().toString())
                .respondedBy(userId)
                        .build();

        comment.setReply(reply);
        Comment comment1 = commentRepository.save(comment);
        Optional<User> user = userRepository.findById(comment.getUser());
        emailService.sendEmail(new EmailDTO("Nuevo comentario en "+place.get().getName(),
                "Su comentario ha sido respondido:  http://localhost:8080/api/comment/create-comment", user.get().getEmail()));
        return comment1;
    }

    @Override
    public String deleteComment(DeleteCommentDTO deleteCommentDTO, String token) throws DeleteCommentException {
        Optional<Comment> commentOptional = commentRepository.findById(deleteCommentDTO.commentId());
        if (commentOptional.isEmpty()) {
            throw new DeleteCommentException("El comentario no existe");
        }

        Jws<Claims> jws = jwtUtils.parseJwt(token);
        String userId = jws.getPayload().get("id").toString();
        if(!commentOptional.get().getUser().equals(userId))
            throw new DeleteCommentException("El usuario no puede eliminar un comentario de otro usuario");

        commentRepository.deleteById(deleteCommentDTO.commentId());
        return commentOptional.get().getId();
    }

    @Override
    public List<CommentResponseDTO> getCommentsByPlace(String placeId) throws ListCommentsException {
        if (!placeRepository.existsById(placeId)) {
            throw new ListCommentsException("El lugar no existe");
        }

        List<Comment> comments = commentRepository.findAllByPlace(placeId);

        return comments.stream()
                .map(this::mapCommentToCommentResponseDTO)
                .toList();
    }

    private CommentResponseDTO mapCommentToCommentResponseDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getDate(),
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
    public Float getAverageScoreByPlace(String placeId) throws GetAverageScoreCommentException {
        if (!placeRepository.existsById(placeId)) {
            throw new GetAverageScoreCommentException("El lugar no existe");
        }
        Float average = commentRepository.findAverageRatingByPlace(placeId);
        return average != null ? average : 0.0f;
    }

}
