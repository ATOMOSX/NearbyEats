package co.edu.uniquindio.proyecto.modelo;

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
