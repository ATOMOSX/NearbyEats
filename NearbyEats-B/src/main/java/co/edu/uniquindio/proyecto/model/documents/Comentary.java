package co.edu.uniquindio.proyecto.model.documents;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "comentarios")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentary {
    private Client client;
    private String commentary;
    private LocalDateTime date;
    private String image;
    private List<Comentary> answers;
}
