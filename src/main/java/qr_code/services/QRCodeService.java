package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.common.ObjectMapperUtil;
import qr_code.endpoints.CheckInEndpoint;

@Service
public class QRCodeService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    public String genQRCore(String email) {
        logger.info("QRCodeService=>genQRCore=>start email#{} ", ObjectMapperUtil.toJsonString(email));
        String qrCodeUrl = "";

        // TODO: Gen QR code

        logger.info("QRCodeService=>genQRCore=>end qrCodeUrl#{} ", ObjectMapperUtil.toJsonString(qrCodeUrl));
        return qrCodeUrl;
    }
}
