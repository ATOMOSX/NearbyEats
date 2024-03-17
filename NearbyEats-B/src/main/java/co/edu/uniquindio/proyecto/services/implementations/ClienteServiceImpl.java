package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.client.ItemClientDTO;
import co.edu.uniquindio.proyecto.dto.client.*;
import co.edu.uniquindio.proyecto.exceptions.client.*;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

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
    public String register(ClientRegistrationDTO clientRegistrationDTO) throws ClientRegistrationException {

        if (existEmail(clientRegistrationDTO.email())) {
            throw new ClientRegistrationException("Este email no se encuentra disponible");
        }

        if (existNickname(clientRegistrationDTO.nickname())){
            throw new ClientRegistrationException("El nickName ya se encuentra disponible");
        }

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

        return registerCliente.getId();
    }

    @Override
    public void updateAccount(UpdateAccountDTO updateAccountDTO) throws UpdateAccountExcepetion {

        Optional<Client> clientOptional = clientRepo.findById(updateAccountDTO.id());

        if (clientOptional.isEmpty()){
            throw new UpdateAccountExcepetion("Id del cliente no puede estar vacio para poder actualizar el cliente");
        }

        Client client = clientOptional.get();
        client.setFirstName(updateAccountDTO.firstName());
        client.setLastName(updateAccountDTO.lastName());
        client.setEmail(updateAccountDTO.email()); // posible problema, hacer una validación para que no exista un correo similar en la BD
        client.setCity(updateAccountDTO.city());
        client.setProfilePhoto(updateAccountDTO.profilePhoto());

    }

    @Override
    public void deleteAccount(String id) throws DeleteAccountException {

        Optional<Client> clientOptional = clientRepo.findById(id);
        if (clientOptional.isEmpty()){
            throw new DeleteAccountException("El id del cliente a eliminar no puede ser vacío");
        }

        Client client = clientOptional.get();
        client.setStatus(Status.INACTIVE);

        clientRepo.save(client);
    }

    @Override
    public void sendRecoveryEmail(String email) throws SendRecoveryEmailException {
        Optional<Client> clientOptional = clientRepo.findByEmail(email);

        if (clientOptional.isEmpty()){
            throw new SendRecoveryEmailException("El email no puede ser vacio");
        }
       // emailService.sendEmail("Cambio de contraseña de NeabryEats", email,
         //     "Para cambiar la contraseña accede al siguiente enlace http://......./params ");
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) throws ChangePasswordException {

        //Validamos el Toker
        Optional<Client> clientOptional1 = clientRepo.findById(changePasswordDTO.idClient());

        if (clientOptional1.isEmpty()){
            throw new ChangePasswordException("El cliente no puede presentar un id vacio");
        }

        Client client = clientOptional1.get();
        client.setPassword(changePasswordDTO.password());
        clientRepo.save(client);
    }

    @Override
    public GetClientDTO getClient(String id) throws GetClientException {
        Optional<Client> clientOptional = clientRepo.findById(id);

        if (clientOptional.isEmpty()){
            throw new GetClientException("El cliente del id a obtener no puede ser vacío");
        }
        Client client = clientOptional.get();

        if (client.getStatus() == Status.INACTIVE){
            throw new GetClientException("No se puede obtener un cliente inactivo");
        }

        return  new GetClientDTO(
                id,
                client.getFirstName(),
                client.getLastName(),
                client.getProfilePhoto(),
                client.getNickname(),
                client.getCity(),
                client.getPassword()
        );
    }

    @Override
    public List<ItemClientDTO> listarClientes() throws Exception {
//        Page<Client> clients = clientRepo.findAll(PageRequest.of(0, 10));

        List<Client> clients = clientRepo.findByStatus(Status.ACTIVE);

        return clients.stream().map(
                c -> new ItemClientDTO(c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getProfilePhoto(),
                c.getNickname(),
                c.getCity())).toList();
    }

    public boolean existNickname(String nickname){
        return clientRepo.findByNickname(nickname).isPresent();
    }

    public boolean existEmail(String email)  {
        return clientRepo.findByEmail(email).isPresent();
    }
}
