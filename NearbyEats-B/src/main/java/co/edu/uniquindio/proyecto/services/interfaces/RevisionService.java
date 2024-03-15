package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.revision.ApprovePlaceDTO;
import co.edu.uniquindio.proyecto.dto.revision.DeclinePlaceDTO;
import co.edu.uniquindio.proyecto.exceptions.revision.ApprovePlaceException;
import co.edu.uniquindio.proyecto.exceptions.revision.DeclinePlaceException;

public interface RevisionService {

    void approvePlace(ApprovePlaceDTO approvePlaceDTO) throws ApprovePlaceException;

    void declinePlace(DeclinePlaceDTO declinePlaceDTO) throws DeclinePlaceException;
}
