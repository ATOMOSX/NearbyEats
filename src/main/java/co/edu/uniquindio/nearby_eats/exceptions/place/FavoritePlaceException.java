package co.edu.uniquindio.nearby_eats.exceptions.place;

public class FavoritePlaceException extends Exception {
    public FavoritePlaceException(String elClienteNoExiste) {
        super(elClienteNoExiste);
    }
}
