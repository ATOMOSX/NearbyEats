package co.edu.uniquindio.proyecto.model.entities;

import co.edu.uniquindio.proyecto.model.enums.Status;
import lombok.*;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String password;
    private Status status;

    public User() {

    }

    @Builder
    public User(String name, String email, String password, Status status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
