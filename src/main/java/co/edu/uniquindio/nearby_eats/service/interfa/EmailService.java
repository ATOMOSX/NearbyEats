package co.edu.uniquindio.nearby_eats.service.interfa;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;
import co.edu.uniquindio.nearby_eats.exceptions.email.EmailServiceException;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws MessagingException, EmailServiceException;
}
