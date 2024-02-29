package co.edu.uniquindio.proyecto.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "moderadores")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Moderator extends User implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private List<Revision> revisions;

}
