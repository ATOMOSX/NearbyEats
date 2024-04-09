package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Reply implements Serializable {
    private String text;
    private String date;
    private String respondedBy;
}
