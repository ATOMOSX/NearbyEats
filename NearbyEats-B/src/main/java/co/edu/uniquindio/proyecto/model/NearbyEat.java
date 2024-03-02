package co.edu.uniquindio.proyecto.model;

import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.documents.Place;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class NearbyEat {
    private List<Place> places;
    private List<Client> clients;
}
