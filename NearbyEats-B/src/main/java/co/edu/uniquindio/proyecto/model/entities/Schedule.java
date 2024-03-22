package co.edu.uniquindio.proyecto.model.entities;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private String dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
