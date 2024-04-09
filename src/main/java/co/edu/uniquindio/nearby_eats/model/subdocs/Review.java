package co.edu.uniquindio.nearby_eats.model.subdocs;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review implements Serializable {
    private String moderatorId;
    private String date;
    private String action;
    private String commentary;
}
