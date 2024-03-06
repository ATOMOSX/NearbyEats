package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.model.documents.Client;

public interface PlaceService {
    void createPlace();

    void deletePlace();
    void updatePlace();
    void searchPlace();
    void filterStatusPlace();
    void listPlaceOwnert();

}
