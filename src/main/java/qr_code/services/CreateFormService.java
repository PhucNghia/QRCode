package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import qr_code.common.APIOutput;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.models.model.QRCode;
import qr_code.repository.QRCodeRepository;

@Service
public class CreateFormService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final QRCodeRepository qrCodeRepository;
    private final QRCodeService qrCodeService;
    private final EmailService emailService;

    public CreateFormService(QRCodeRepository qrCodeRepository, QRCodeService qrCodeService, EmailService emailService) {
        this.qrCodeRepository = qrCodeRepository;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
    }

    public APIOutput create(QRCode qrCode) {
        /* Gen QR CODE */
        String qrCodeUrl = qrCodeService.genQRCore(qrCode.getContent());
        /* Save to DB */
        qrCode.setQrCodeUrl(qrCodeUrl);
        APIOutput apiOutput = new APIOutput();

        if (qrCodeRepository.countAllByToAddressIgnoreCase(qrCode.getToAddress()) == 0){
            qrCode = qrCodeRepository.save(qrCode);
            apiOutput.setData(qrCode);
            emailService.sendEmail(qrCode.getToAddress(),"Helloworld",qrCode.getQrCodeUrl());
        }else{
            apiOutput.setStatus(2);
            apiOutput.setData(null);
        }
        return apiOutput;
    }

}
