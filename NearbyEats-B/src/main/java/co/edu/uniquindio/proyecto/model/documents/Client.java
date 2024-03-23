package co.edu.uniquindio.proyecto.model.documents;

import co.edu.uniquindio.proyecto.model.entities.User;
import co.edu.uniquindio.proyecto.model.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Document(collection = "clientes")
@ToString
public class Client extends User implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String profilePhoto;
    private String nickname;
    private String city;
    private List<Place> createdPlaces;
    private List<Place> favoritePlaces;

    @Builder
    public Client(String firstName, String lastName, String email, String password,
                  Status status, String id, String profilePhoto, String nickname,
                  String city, List<Place> createdPlaces, List<Place> favoritePlaces) {
        super(firstName, lastName, email, password, status);
        this.id = id;
        this.profilePhoto = profilePhoto;
        this.nickname = nickname;
        this.city = city;
        this.createdPlaces = createdPlaces;
        this.favoritePlaces = favoritePlaces;
    }
}
