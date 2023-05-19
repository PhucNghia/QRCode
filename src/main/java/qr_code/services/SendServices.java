package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.endpoints.CheckInEndpoint;

@Service
public class SendServices {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    public void sendEmail(String to, String contentEmail) {
        logger.info("SendServices=>sendEmail=>start to#{}, content#{} ", to, contentEmail);

        // TODO: send email

        logger.info("SendServices=>sendEmail=>end to#{}, content#{} ", to, contentEmail);
    }
}
