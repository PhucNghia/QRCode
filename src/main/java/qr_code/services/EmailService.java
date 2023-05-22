package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.utils.QRCodeUtil;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final TaskExecutor taskExecutor;

    private final  QRCodeUtil qrCodeUtil;

    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    public EmailService(JavaMailSender javaMailSender, TaskExecutor taskExecutor, QRCodeUtil qrCodeUtil) {
        this.javaMailSender = javaMailSender;
        this.taskExecutor = taskExecutor;
        this.qrCodeUtil = qrCodeUtil;
    }

    public void sendEmail(String to, String subject) {
        taskExecutor.execute(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h1>My First Heading</h1>\n" +
                        "\n" +
                        "<p>My first paragraph.</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n" +
                        "\n", true);
                javaMailSender.send(message);
            } catch (Exception exception) {
                logger.error("Exception send email:" + exception.getMessage());
            }
        });
    }
}