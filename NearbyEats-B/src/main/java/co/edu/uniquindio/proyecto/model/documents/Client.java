package co.edu.uniquindio.proyecto.model.documents;

import co.edu.uniquindio.proyecto.model.entities.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "clientes")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends User implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String profilePhoto;
    private String nickname;
    private String city;
    private List<Place> createdPlaces;
    private List<Place> favoritePlaces;

}
