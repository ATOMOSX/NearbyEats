package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@ToString

public class Ubication {

    @Id
    private String id;
    private float latitude;
    private float length;
}
