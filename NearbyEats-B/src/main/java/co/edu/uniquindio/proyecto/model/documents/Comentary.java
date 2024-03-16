package co.edu.uniquindio.proyecto.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
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

    @Id
    private String id;
    private String nickName;
    private String commentary;
    private LocalDateTime date;
    private String image;
    private Comentary answer;
    private String score;

}
