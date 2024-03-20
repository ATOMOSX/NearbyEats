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
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ComentaryServiceImpl implements CommentaryService {

    private final CommentaryRepo comentaryRepo;
    private final ClientService clientService;
    private final PlaceService placeService;

    @Override
    public void comment(CommentDTO commentDTO) throws CommentException {
       /* userId,
                @NotBlank(message = "Place is required") @Length(max = 100) String placeId,
                @NotBlank(message = "Comment is required") String comment,
                @NotBlank(message = "Score is required") String score,
                @NotBlank(message = "Date is required") @DateTimeFormat LocalDateTime date*/

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
        /*
        b.comentarios.aggregate(
	{
		$match: {codigoNegocio: "Negocio1"}
	},
	{
		$lookup:
		{
			from: "clientes",
			localField: "codigoCliente",
			foreignField: "_id",
			as: "ciente"
		}
	},
	{
		$projection: { nombreCliente: "$cliente.nombre", fotoCliente: "$cliente.foto"}
	}
)
         */

        return response;
    }

    @Override
    public void answerComment(AnswerCommentDTO answerCommentDTO) {

    }

    @Override
    public void calculateAvarageScore(int score) throws CalculateAverageScoreException {

    }
}
