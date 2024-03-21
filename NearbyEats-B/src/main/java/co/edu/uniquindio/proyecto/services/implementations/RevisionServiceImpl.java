package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.revision.ApprovePlaceDTO;
import co.edu.uniquindio.proyecto.dto.revision.DeclinePlaceDTO;
import co.edu.uniquindio.proyecto.exceptions.revision.ApprovePlaceException;
import co.edu.uniquindio.proyecto.exceptions.revision.DeclinePlaceException;
import co.edu.uniquindio.proyecto.services.interfaces.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RevisionServiceImpl implements RevisionService{
    @Override
    public void approvePlace(ApprovePlaceDTO approvePlaceDTO) throws ApprovePlaceException {

    }

    @Override
    public void declinePlace(DeclinePlaceDTO declinePlaceDTO) throws DeclinePlaceException {

    }
}
