package co.edu.uniquindio.proyecto.model.documents;

import co.edu.uniquindio.proyecto.model.documents.Moderator;
import co.edu.uniquindio.proyecto.model.enums.RevisionStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "revisiones")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Revision implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private RevisionStatus revisionStatus;
    private String description;
    private LocalDateTime date;
    private Moderator moderator;

}
