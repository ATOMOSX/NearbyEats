package co.edu.uniquindio.proyecto.model.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "comentarios")
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Commentary {

    @Id
    private String id;
    private String nickName;
    private String placeId;
    private String commentary;
    private LocalDateTime date;
    private Commentary answer;
    private int score;

}
