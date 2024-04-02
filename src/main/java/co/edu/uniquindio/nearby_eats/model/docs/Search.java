package co.edu.uniquindio.nearby_eats.model.docs;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "searches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Search implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String user;

    private String query;

    private LocalDateTime date;
}
