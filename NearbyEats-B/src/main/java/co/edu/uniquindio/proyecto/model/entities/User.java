package co.edu.uniquindio.proyecto.model.entities;

import co.edu.uniquindio.proyecto.model.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Status status;
}
