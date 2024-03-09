package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.client.*;
import co.edu.uniquindio.proyecto.exceptions.client.*;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.entities.User;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClientService {

    private final ClientRepo clientRepo;


    public ClienteServiceImpl(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public String login(ClientLoginDTO clientLoginDTO) throws ClientLoginException {
        return null;
    }

    @Override
    public void register(ClientRegistrationDTO clientRegistrationDTO) throws ClientRegistrationException {

        Client client = Client.builder()
                .nickname(clientRegistrationDTO.nickname())
                .city(clientRegistrationDTO.city())
                .profilePhoto(clientRegistrationDTO.profilePhoto())
                .firstName(clientRegistrationDTO.firstName())
                .lastName(clientRegistrationDTO.lastName())
                .password(clientRegistrationDTO.password())
                .status(Status.ACTIVE)
                .email(clientRegistrationDTO.email())
                .build();

        Client registerCliente = clientRepo.save(client);

    }

    public boolean existNickname(String nickname){

       return clientRepo.findByNickname(nickname).isPresent();

    }

    public boolean existEmail(String email)  {

        return clientRepo.findByEmail(email).isPresent();
    }

    @Override
    public void updateAccount(UpdateAccountDTO updateAccountDTO) throws UpdateAccountExcepetion {

    }

    @Override
    public void deleteAccount(String id) throws DeleteAccountException {

    }

    @Override
    public void sendRecoveryEmail(String email) throws SendRecoveryEmailException {

    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException {

    }

    @Override
    public GetClientDTO getClient(String id) throws GetClientException {
        return null;
    }
}
