package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.comment.AnswerCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.CommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ListCommensDTO;
import co.edu.uniquindio.proyecto.exceptions.comment.AnwerCommetException;
import co.edu.uniquindio.proyecto.exceptions.comment.CalculateAverageScoreException;
import co.edu.uniquindio.proyecto.exceptions.comment.CommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.ListCommentsExeception;
import co.edu.uniquindio.proyecto.model.documents.Place;

import java.util.List;

public interface ComentaryService {
    void comment(CommentDTO commentDTO) throws CommentException;

    List<Place> listComments(ListCommensDTO listCommensDTO) throws ListCommentsExeception;

    void answerComment(AnswerCommentDTO answerCommentDTO) throws AnwerCommetException;

    void calculateAvarageScore(int score) throws CalculateAverageScoreException;

}
