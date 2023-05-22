package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.common.ObjectMapperUtil;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.utils.QRCodeUtil;

@Service
public class QRCodeService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    private final QRCodeUtil qrCodeUtil;

    public QRCodeService(QRCodeUtil qrCodeUtil) {
        this.qrCodeUtil = qrCodeUtil;
    }

    public String genQRCore(String email) {
        try {
            logger.info("QRCodeService=>genQRCore=>start email#{} ", ObjectMapperUtil.toJsonString(email));
            String qrCodeUrl = "";
            qrCodeUrl = qrCodeUtil.generateQRCodeURL(email, 300, 300);

            // TODO: Gen QR code
            logger.info("QRCodeService=>genQRCore=>end qrCodeUrl#{} ", ObjectMapperUtil.toJsonString(qrCodeUrl));
            return qrCodeUrl;
        } catch (Exception exception) {
            return null;
        }
    }

}
