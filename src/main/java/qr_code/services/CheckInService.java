package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.common.ObjectMapperUtil;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.models.QRCode;
import qr_code.repository.QRCodeRepository;

@Service
public class CheckInService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final QRCodeRepository qrCodeRepository;
    private final QRCodeService barCodeService;
    private final SendServices sendServices;

    public CheckInService(QRCodeRepository qrCodeRepository, QRCodeService barCodeService, SendServices sendServices) {
        this.qrCodeRepository = qrCodeRepository;
        this.barCodeService = barCodeService;
        this.sendServices = sendServices;
    }

    public QRCode create(QRCode qrCode) {
        logger.info("CheckInService=>create=>start qrCode#{} ", ObjectMapperUtil.toJsonString(qrCode));

        /* Gen QR CODE */
        String qrCodeUrl = barCodeService.genQRCore(qrCode.getContent());

        /* Save to DB */
        qrCode.setQrCodeUrl(qrCodeUrl);
        qrCode = qrCodeRepository.save(qrCode);

        /* Send Email */
        String contentEmail = "";
        sendServices.sendEmail(qrCode.getToAddress(), contentEmail);

        logger.info("CheckInService=>create=>end qrCode#{} ", ObjectMapperUtil.toJsonString(qrCode));
        return qrCode;
    }
}
