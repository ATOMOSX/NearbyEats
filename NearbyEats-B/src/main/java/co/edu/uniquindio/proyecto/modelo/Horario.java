package co.edu.uniquindio.proyecto.modelo;

import org.springframework.data.annotation.Id;

import java.time.LocalTime;

public class Horario {

    @Id
    private String id;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
