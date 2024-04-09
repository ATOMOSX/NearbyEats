package co.edu.uniquindio.nearby_eats.model.docs;

import co.edu.uniquindio.nearby_eats.model.subdocs.Reply;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String text;

    private String user;

    private String place;

    private String date;

    private Integer rating;

    private Reply reply;
}
