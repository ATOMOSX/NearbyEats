package co.edu.uniquindio.nearby_eats.service;

import co.edu.uniquindio.nearby_eats.dto.email.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
