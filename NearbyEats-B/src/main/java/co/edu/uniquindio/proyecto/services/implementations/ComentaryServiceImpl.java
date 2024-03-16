package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.client.GetClientDTO;
import co.edu.uniquindio.proyecto.dto.comment.AnswerCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.CommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ItemCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ListCommentsDTO;
import co.edu.uniquindio.proyecto.exceptions.client.GetClientException;
import co.edu.uniquindio.proyecto.exceptions.comment.CalculateAverageScoreException;
import co.edu.uniquindio.proyecto.exceptions.comment.CommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.ListCommentsExeception;
import co.edu.uniquindio.proyecto.model.documents.Comentary;
import co.edu.uniquindio.proyecto.repository.CommentaryRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ClientService;
import co.edu.uniquindio.proyecto.services.interfaces.CommentaryService;
import co.edu.uniquindio.proyecto.services.interfaces.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
public class ComentaryServiceImpl implements CommentaryService {

    private final CommentaryRepo comentaryRepo;
    private final ClientService clientService;
    private final PlaceService placeService;

    @Override
    public void comment(CommentDTO commentDTO) throws CommentException {

    }

    @Override
    public List<ItemCommentDTO> listComments(ListCommentsDTO listCommensDTO) throws ListCommentsExeception, GetClientException {

        List<Comentary> comentaries = comentaryRepo.findByIdPlace(listCommensDTO.idPlace());
        List<ItemCommentDTO> response = new ArrayList<>();

        for (Comentary c : comentaries) {
            GetClientDTO getClientDTO = clientService.getClient(c.getId());

            response.add( new ItemCommentDTO(
                    c.getId(),
                    c.getCommentary(),
                    c.getAnswer(),
                    getClientDTO.firstName(),
                    getClientDTO.lastName(),
                    getClientDTO.profilePhoto(),
                    c.getDate().toString(),
                    c.getScore()
            ));
        }

        return response;
    }

    @Override
    public void answerComment(AnswerCommentDTO answerCommentDTO) {

    }

    @Override
    public void calculateAvarageScore(int score) throws CalculateAverageScoreException {

    }
}
