package co.edu.uniquindio.proyecto.model.entities;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
public class Schedule {
    private String dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
