package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.common.ObjectMapperUtil;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.utils.QRCodeUtil;

import java.util.Base64;

@Service
public class QRCodeService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);

    private final QRCodeUtil qrCodeUtil;

    public QRCodeService(QRCodeUtil qrCodeUtil) {
        this.qrCodeUtil = qrCodeUtil;
    }

    public String genQRCore(String email) {
        try {
            byte[] qrCode = qrCodeUtil.generateQRCodeWithBackground(email);
            return Base64.getEncoder().encodeToString(qrCode);
        } catch (Exception exception) {
            return null;
        }
    }

}
