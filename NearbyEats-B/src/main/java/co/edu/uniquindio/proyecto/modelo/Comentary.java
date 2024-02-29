package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class Comentary {
    private Client client;
    private String commentary;
    private String image;
    private List<Comentary> answers;
}
