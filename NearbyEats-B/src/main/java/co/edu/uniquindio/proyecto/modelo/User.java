package co.edu.uniquindio.proyecto.modelo;

import lombok.*;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String password;
    private Status status;
}
