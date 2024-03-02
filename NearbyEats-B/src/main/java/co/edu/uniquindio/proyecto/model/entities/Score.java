package co.edu.uniquindio.proyecto.model.entities;

import co.edu.uniquindio.proyecto.model.documents.Client;
import lombok.*;

@Getter
@Setter
public class Score {
    private Client client;
    private float score;
}
