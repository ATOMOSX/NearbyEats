package co.edu.uniquindio.proyecto.model.documents;

import co.edu.uniquindio.proyecto.model.entities.Schedule;
import co.edu.uniquindio.proyecto.model.entities.Score;
import co.edu.uniquindio.proyecto.model.entities.Ubication;
import co.edu.uniquindio.proyecto.model.enums.Category;
import co.edu.uniquindio.proyecto.model.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "lugares")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Place implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;
    private String description;
    private Ubication location;
    private List<String> pictures;
    private List<Schedule> schedule;
    private List<String> phones;
    private List<Category> categories;
    private List<Comentary> commentaries;
    private List<Score> scores;
    private List<Revision> revisionsHistory;
    private Status status;


}
