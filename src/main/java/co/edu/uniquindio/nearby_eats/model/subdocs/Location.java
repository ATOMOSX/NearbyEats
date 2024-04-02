package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Location implements Serializable {
    private String type; // Point
    private List<Double> coordinates; // [longitude, latitude]
}
