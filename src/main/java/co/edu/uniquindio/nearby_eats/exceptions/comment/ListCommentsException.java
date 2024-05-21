package co.edu.uniquindio.nearby_eats.exceptions.comment;

public class ListCommentsException extends Exception {
    public ListCommentsException(String elLugarNoExiste) {
        super(elLugarNoExiste);
    }
}
