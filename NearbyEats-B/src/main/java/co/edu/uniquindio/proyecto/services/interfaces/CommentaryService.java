package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.comment.AnswerCommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.CommentDTO;
import co.edu.uniquindio.proyecto.dto.comment.ListCommentsDTO;
import co.edu.uniquindio.proyecto.exceptions.comment.AnswerCommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.CalculateAverageScoreException;
import co.edu.uniquindio.proyecto.exceptions.comment.CommentException;
import co.edu.uniquindio.proyecto.exceptions.comment.ListCommentsExeception;
import co.edu.uniquindio.proyecto.model.documents.Place;

import java.util.List;

public interface CommentaryService {
    void comment(CommentDTO commentDTO) throws CommentException;

    List<Place> listComments(ListCommentsDTO listCommentsDTO) throws ListCommentsExeception;

    void answerComment(AnswerCommentDTO answerCommentDTO) throws AnswerCommentException;

    void calculateAvarageScore(int score) throws CalculateAverageScoreException;

}