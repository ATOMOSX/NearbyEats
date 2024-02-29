package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class NearbyEat {
    private List<Place> places;
    private List<Client> clients;
}
