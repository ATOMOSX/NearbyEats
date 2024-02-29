package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
public class Schedule {
    private String dayOfWeek;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
