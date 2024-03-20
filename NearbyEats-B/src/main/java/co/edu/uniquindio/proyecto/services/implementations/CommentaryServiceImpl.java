package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.client.GetClientDTO;
import co.edu.uniquindio.proyecto.dto.comment.AnswerCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.CommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ItemCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ListCommentsDTO;
import co.edu.uniquindio.proyecto.dto.place.GetPlaceDTO;
import co.edu.uniquindio.proyecto.exceptions.client.GetClientException;
import co.edu.uniquindio.proyecto.exceptions.comment.AnswerCommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.CommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.ListCommentsExeception;
import co.edu.uniquindio.proyecto.exceptions.place.GetPlaceException;
import co.edu.uniquindio.proyecto.model.documents.Commentary;
import co.edu.uniquindio.proyecto.repository.CommentaryRepo;
import co.edu.uniquindio.proyecto.services.interfaces.CommentaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepo commentaryRepo;
    private final ClientServiceImpl clientServiceImpl;
    private final PlaceServiceImpl placeServiceImpl;

    @Override
    public void comment(CommentDTO commentDTO) throws CommentException, GetClientException, GetPlaceException {
        GetClientDTO client = clientServiceImpl.getClient(commentDTO.clientId());
        GetPlaceDTO place = placeServiceImpl.getPlace(commentDTO.placeId());

        Commentary commentary = Commentary.builder()
                .nickName(client.nickname())
                .commentary(commentDTO.comment())
                .date(commentDTO.date())
                .score(commentDTO.score())
                .placeId(place.id())
                .build();

        commentaryRepo.save(commentary);
    }

    @Override
    public List<ItemCommentDTO> listComments(String placeID) throws ListCommentsExeception {
        if(commentaryRepo.listComments(placeID).isEmpty())
            throw new ListCommentsExeception("El lugar no tiene comentarios");

        return commentaryRepo.listComments(placeID);
    }

    @Override
    public void answerComment(AnswerCommentDTO answerCommentDTO) throws AnswerCommentException, GetClientException, GetPlaceException {
        GetClientDTO client = clientServiceImpl.getClient(answerCommentDTO.idUser());
        GetPlaceDTO place = placeServiceImpl.getPlace(answerCommentDTO.idPlace());
        Optional<Commentary> commentaryOptional = commentaryRepo.findById(answerCommentDTO.idComment());

        if (commentaryOptional.isEmpty())
            throw new AnswerCommentException("El comentario a responder no existe");

        Commentary answer = Commentary.builder()
                .nickName(client.nickname())
                .placeId(place.id())
                .commentary(answerCommentDTO.answer())
                .date(answerCommentDTO.date())
                .build();

        Commentary originalComment = commentaryOptional.get();
        originalComment.setAnswer(answer);
        commentaryRepo.save(originalComment);
    }

}
