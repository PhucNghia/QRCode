package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import qr_code.endpoints.CheckInEndpoint;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final TaskExecutor taskExecutor;

    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    public EmailService(JavaMailSender javaMailSender, TaskExecutor taskExecutor) {
        this.javaMailSender = javaMailSender;
        this.taskExecutor = taskExecutor;
    }

    public void sendEmail(String to, String subject, String content) {
        taskExecutor.execute(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
                helper.setTo(to);
                helper.setSubject(subject);
                byte[] dataQR = DatatypeConverter.parseBase64Binary(content);
                ByteArrayDataSource dataSource = new ByteArrayDataSource(dataQR, "image/png");
                helper.addAttachment(removeEndPrefixEmail(to) + ".png", dataSource);
                String variable = "<!DOCTYPE html>"+
                        "<body>"+
                        "\n"+
                        "<h1>My First Heading</h1>"+
                        "\n"+
                        "<p>My first paragraph.</p>"+
                        "\n"+
                        "<img src='cid:imageId'/>" +
                        "\n"+
                        "</body>"+
                        "</html>";
                helper.setText(variable, true);
                javaMailSender.send(message);
            } catch (Exception exception) {
                logger.error("Exception send email:" + exception.getMessage());
            }
        });
    }

    public static String removeEndPrefixEmail(String email) {
        int atIndex = email.lastIndexOf("@");

        if (atIndex != -1) {
            return email.substring(0, atIndex);
        }
        return "null";
    }
}