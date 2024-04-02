package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Reply implements Serializable {
    private String text;
    private LocalDateTime date;
    private String respondedBy;
}
