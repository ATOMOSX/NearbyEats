package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.moderator.ModeratorChangePasswordDTO;
import co.edu.uniquindio.proyecto.dto.moderator.ModeratorLoginDTO;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorChangePassworException;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorLoginException;
import co.edu.uniquindio.proyecto.services.interfaces.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {
    @Override
    public void login(ModeratorLoginDTO moderatorLoginDTO) throws ModeratorLoginException {

    }

    @Override
    public void changePassword(ModeratorChangePasswordDTO moderatorChangePasswordDTO) throws ModeratorChangePassworException {

    }
}
