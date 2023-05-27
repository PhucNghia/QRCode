package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.models.model.Config;
import qr_code.repository.ConfigRepository;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final TaskExecutor taskExecutor;

    private final ConfigService configService;

    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    public EmailService(JavaMailSender javaMailSender, TaskExecutor taskExecutor, ConfigService configService) {
        this.javaMailSender = javaMailSender;
        this.taskExecutor = taskExecutor;
        this.configService = configService;
    }

    public void sendEmail(String to, String content) {
        taskExecutor.execute(() -> {
            try {
                Config config = configService.getLastConfig();
                if (config == null){
                    return;
                }
                String htmlConfig = configService.getLastConfig().getHtmlConfig();
                String subject = configService.getLastConfig().getSubject();
                if (StringUtils.isEmpty(htmlConfig) && StringUtils.isEmpty(subject)){
                    return;
                }
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
                helper.setTo(to);
                helper.setSubject(subject);
                byte[] dataQR = DatatypeConverter.parseBase64Binary(content);
                ByteArrayDataSource dataSource = new ByteArrayDataSource(dataQR, "image/png");
                helper.addAttachment(removeEndPrefixEmail(to) + ".png", dataSource);
                helper.setText(htmlConfig, true);
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
        return "";
    }
}