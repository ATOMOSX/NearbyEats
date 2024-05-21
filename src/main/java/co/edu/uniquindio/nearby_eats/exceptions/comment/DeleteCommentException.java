package co.edu.uniquindio.nearby_eats.exceptions.comment;

public class DeleteCommentException extends Exception {
    public DeleteCommentException(String elComentarioNoExiste) {
        super(elComentarioNoExiste);
    }
}
