package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.moderator.ModeratorChangePasswordDTO;
import co.edu.uniquindio.proyecto.dto.moderator.ModeratorLoginDTO;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorChangePassworException;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorLoginException;

public interface
ModeratorService {
    void login(ModeratorLoginDTO moderatorLoginDTO) throws ModeratorLoginException;

    void changePassword(ModeratorChangePasswordDTO moderatorChangePasswordDTO) throws ModeratorChangePassworException;

}
