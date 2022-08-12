package br.com.sosvip.apigerenciamentopedidos.aws.ses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SESService {

    @Autowired
    private MailSender mailSender;

    static final String FROM = (System.getenv("AWS_EMAIL_SENDER"));
    static final String SUBJECT = "S.O.S VIP - Agradece seu pedido!";
    static final String TEXTBODY = "Informamos que neste momento o seu pedido encontra-se: Confirmado\n" + "\nA empresa SOS VIP agradece o seu pedido!!";


    public Boolean sendMessage(String clientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(clientEmail);
        message.setSubject(SUBJECT);
        message.setText(TEXTBODY);

        sendEmail(message);

        return true;
    }

    public void sendEmail(SimpleMailMessage message) {
        this.mailSender.send(message);
    }
}
