package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.moderator.ModeratorChangePasswordDTO;
import co.edu.uniquindio.proyecto.dto.moderator.ModeratorLoginDTO;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorChangePassworException;
import co.edu.uniquindio.proyecto.exceptions.moderator.ModeratorLoginException;
import co.edu.uniquindio.proyecto.model.documents.Moderator;
import co.edu.uniquindio.proyecto.repository.ModeratorRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {

    private final ModeratorRepo moderatorRepo;
    @Override
    public void login(ModeratorLoginDTO moderatorLoginDTO) throws ModeratorLoginException {

        Optional<Moderator> moderatorOptional = moderatorRepo.findByNickname(moderatorLoginDTO.nickname());

        if (moderatorOptional.isEmpty()){
            throw new ModeratorLoginException("El nickName no existe");
        }

        if (!moderatorOptional.get().getPassword().equals(moderatorLoginDTO.password())){
            throw new ModeratorLoginException("");
        }
    }

    @Override
    public void changePassword(ModeratorChangePasswordDTO moderatorChangePasswordDTO) throws ModeratorChangePassworException {

    }
}
