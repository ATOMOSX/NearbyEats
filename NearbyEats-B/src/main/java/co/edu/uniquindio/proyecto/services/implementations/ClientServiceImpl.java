package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.client.ItemClientDTO;
import co.edu.uniquindio.proyecto.dto.client.*;
import co.edu.uniquindio.proyecto.exceptions.client.*;
import co.edu.uniquindio.proyecto.model.documents.Client;
import co.edu.uniquindio.proyecto.model.enums.Status;
import co.edu.uniquindio.proyecto.repository.ClientRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private Set<String> forbiddenNickName;

    @Override
    public String login(ClientLoginDTO clientLoginDTO) throws ClientLoginException {
        Optional<Client> clientOptional = clientRepo.findByNickname(clientLoginDTO.nickname());

        if (clientOptional.isEmpty()){
            throw new ClientLoginException("El nickname no existe");
        }

        if (!clientOptional.get().getPassword().equals(clientLoginDTO.password()))
            throw new ClientLoginException("La contraseña es incorrecta");

        return "token";
    }

    @Override
    public String register(ClientRegistrationDTO clientRegistrationDTO) throws ClientRegistrationException {
        if (existsEmail(clientRegistrationDTO.email())) {
            throw new ClientRegistrationException("Este email ya se encuentra registrado");
        }

        if (existsNickname(clientRegistrationDTO.nickname())){
            throw new ClientRegistrationException("El nickName ya se encuentra registrado");
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
            throw new UpdateAccountExcepetion("El id no puede estar vacio para poder actualizar el cliente");
        }

        if(existsEmail(updateAccountDTO.email()))
            throw new UpdateAccountExcepetion("El email ya existe en la base de datos");

        Client client = clientOptional.get();
        client.setFirstName(updateAccountDTO.firstName());
        client.setLastName(updateAccountDTO.lastName());
        client.setEmail(updateAccountDTO.email());
        client.setCity(updateAccountDTO.city());
        client.setProfilePhoto(updateAccountDTO.profilePhoto());
        clientRepo.save(client);
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
        Optional<Client> clientOptional = clientRepo.findById(changePasswordDTO.id());

        if (clientOptional.isEmpty()){
            throw new ChangePasswordException("El cliente no puede presentar un id vacio");
        }

        //if(!token.equals(changePasswordDTO.password())
        //throw new ChangePasswordException("Token inválido");

        Client client = clientOptional.get();
        client.setPassword(changePasswordDTO.password());
        clientRepo.save(client);
    }

    @Override
    public GetClientDTO getClient(String id) throws GetClientException {
        // falta litsa de lugares creados y favoritos
        Optional<Client> clientOptional = clientRepo.findById(id);

        if (clientOptional.isEmpty()){
            throw new GetClientException("El id no puede ser vacío");
        }
        Client client = clientOptional.get();

        if (client.getStatus() == Status.INACTIVE){
            throw new GetClientException("No se puede obtener un cliente inactivo");
        }

        return  new GetClientDTO(
                id,
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getNickname(),
                client.getProfilePhoto(),
                client.getCity(),
                client.getPassword()
        );
    }

    @Override
    public List<ItemClientDTO> listClients() throws ListClientException {
//        Page<Client> clients = clientRepo.findAll(PageRequest.of(0, 10));

        List<Client> clients = clientRepo.findByStatus(Status.ACTIVE);

        return clients.stream().map(
                c -> new ItemClientDTO(c.getId(),
                        // falta litsa de lugares creados y favoritos
                c.getFirstName(),
                c.getLastName(),
                c.getProfilePhoto(),
                c.getNickname(),
                c.getEmail(),
                c.getCity())).toList();
    }

    private void uploadForbiddenName() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("usuarios_prohibidos.txt"))) {
            String nickName;
            while ((nickName = bufferedReader.readLine() ) != null) {
                forbiddenNickName.add(nickName.trim());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean isForbiddenNickName (String nickName) {return forbiddenNickName.contains(nickName);}

    public boolean existsNickname(String nickname){
        return clientRepo.findByNickname(nickname).isPresent();
    }

    public boolean existsEmail(String email)  {
        return clientRepo.findByEmail(email).isPresent();
    }
}
