package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Schedule implements Serializable {
    private String weekday;
    private String openingTime;
    private String closingTime;
}
