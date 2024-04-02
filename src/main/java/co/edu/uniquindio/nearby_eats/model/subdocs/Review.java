package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Review implements Serializable {
    private String moderator;
    private LocalDate date;
    private String action;
    private String comment;
}
