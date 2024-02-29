package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "clientes")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends User implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String profilePhoto;
    private String nickname;
    private List<Place> createdPlaces;
    private List<Place> favoritePlaces;

}
