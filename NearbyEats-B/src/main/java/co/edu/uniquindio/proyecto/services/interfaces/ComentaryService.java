package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.CommentDTO;
import co.edu.uniquindio.proyecto.dto.ListCommensDTO;
import co.edu.uniquindio.proyecto.exceptions.CommentException;
import co.edu.uniquindio.proyecto.model.documents.Place;

import java.util.List;

public interface ComentaryService {
    void comment(CommentDTO commentDTO) throws CommentException;

    List<Place> listComments(ListCommensDTO listCommensDTO);

    void answerComment();

    void calculateAvarageScore();

}
