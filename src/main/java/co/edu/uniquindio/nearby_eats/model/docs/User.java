package co.edu.uniquindio.nearby_eats.model.docs;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String firstName;

    private String lastName;

    private String profilePicture;

    private String nickname;

    private String email;

    @ToString.Exclude
    private String password;

    private String city;

    private List<String> roles;

    private Boolean isActive = true;

    private List<String> favoritePlaces;

    private List<String> createdPlaces;

}