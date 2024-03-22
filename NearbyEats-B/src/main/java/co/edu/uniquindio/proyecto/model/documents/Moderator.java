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
@Document(collection = "moderadores")
@ToString
public class Moderator extends User implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private List<Revision> revisions;

    @Builder
    public Moderator(String firstName, String lastName, String email,
                     String password, Status status, String id,
                     List<Revision> revisions) {
        super(firstName, lastName, email, password, status);
        this.id = id;
        this.revisions = revisions;
    }
}
