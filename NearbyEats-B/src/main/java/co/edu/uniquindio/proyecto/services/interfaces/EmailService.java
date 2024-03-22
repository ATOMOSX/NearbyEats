package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.email.EmailDTO;
import co.edu.uniquindio.proyecto.exceptions.email.EmailServiceException;
import jakarta.mail.MessagingException;


public interface EmailService {

    void sendEmail(EmailDTO emailDTO) throws EmailServiceException, MessagingException;
}
