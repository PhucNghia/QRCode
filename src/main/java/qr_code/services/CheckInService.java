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
    private final QRCodeService qrCodeService;
    private final SendServices sendServices;
    private final EmailService emailService;

    public CheckInService(QRCodeRepository qrCodeRepository, QRCodeService qrCodeService, SendServices sendServices, EmailService emailService) {
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeService = qrCodeService;
        this.sendServices = sendServices;
        this.emailService = emailService;
    }

    public QRCode create(QRCode qrCode) {
        logger.info("CheckInService=>create=>start qrCode#{} ", ObjectMapperUtil.toJsonString(qrCode));

        /* Gen QR CODE */
        String qrCodeUrl = qrCodeService.genQRCore(qrCode.getContent());

        /* Save to DB */
        qrCode.setQrCodeUrl(qrCodeUrl);
        qrCode = qrCodeRepository.save(qrCode);

        /* Send Email */
        String contentEmail = "";
        emailService.sendEmail(qrCode.getToAddress(),"Helloworld");

        logger.info("CheckInService=>create=>end qrCode#{} ", ObjectMapperUtil.toJsonString(qrCode));
        return qrCode;
    }
}
