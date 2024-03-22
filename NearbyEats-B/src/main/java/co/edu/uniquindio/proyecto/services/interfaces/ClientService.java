package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.client.*;
import co.edu.uniquindio.proyecto.exceptions.client.*;
import co.edu.uniquindio.proyecto.exceptions.email.EmailServiceException;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ClientService {

    String login(ClientLoginDTO clientLoginDTO) throws ClientLoginException;

    String register(ClientRegistrationDTO clientRegistrationDTO) throws ClientRegistrationException;

    void updateAccount(UpdateAccountDTO updateAccountDTO) throws UpdateAccountExcepetion;

    void deleteAccount(String id) throws DeleteAccountException;

    void sendRecoveryEmail(String email) throws SendRecoveryEmailException, MessagingException, EmailServiceException;

    void changePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException;

    GetClientDTO getClient(String id) throws GetClientException;

    List<ItemClientDTO> listClients() throws ListClientException;

}
