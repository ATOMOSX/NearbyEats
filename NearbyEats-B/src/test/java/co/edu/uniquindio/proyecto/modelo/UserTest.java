package co.edu.uniquindio.proyecto.modelo;

import co.edu.uniquindio.proyecto.repositorio.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


//Volver hacer la prueba despues de volver a configurar todo el código con las anotaciones necesarias.
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUserTest() {

        User user = new User("img", "Armenia", new Date());
        User userRegistrado = userRepository.save(user);

    }
}
