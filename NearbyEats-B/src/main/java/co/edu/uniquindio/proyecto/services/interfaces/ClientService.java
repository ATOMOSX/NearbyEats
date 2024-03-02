package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.ChangePasswordDTO;
import co.edu.uniquindio.proyecto.dto.ClientLoginDTO;
import co.edu.uniquindio.proyecto.dto.ClientRegistrationDTO;
import co.edu.uniquindio.proyecto.dto.UpdateAccountDTO;

public interface ClientService {
    void login(ClientLoginDTO clientLoginDTO);

    void register(ClientRegistrationDTO clientRegistrationDTO) throws Exception;

    void updateAccount(UpdateAccountDTO updateAccountDTO);

    void deleteAccount(String id);

    void sendRecoveryEmail(String email);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    GetClientDTO getClient(String id);

}
