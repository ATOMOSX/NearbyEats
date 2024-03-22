package co.edu.uniquindio.proyecto.services.implementations;

import co.edu.uniquindio.proyecto.dto.email.EmailDTO;
import co.edu.uniquindio.proyecto.exceptions.email.EmailServiceException;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailDTO emailDTO) throws EmailServiceException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setSubject(emailDTO.affair());
        helper.setText(emailDTO.messageBody());
        helper.setTo(emailDTO.addressee());
        helper.setFrom("no_reply@dominio.com");

        javaMailSender.send(message);
    }
}
