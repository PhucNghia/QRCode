package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
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
        if (StringUtils.isEmpty(qrCode.getToAddress())
                || StringUtils.isEmpty(qrCode.getContent())
                || StringUtils.isEmpty(qrCode.getExperience())
                || StringUtils.isEmpty(qrCode.getLevel())
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        /* Gen QR CODE */
        String qrCodeUrl = qrCodeService.genQRCore(qrCode.getContent());
        /* Save to DB */
        qrCode.setQrCodeUrl(qrCodeUrl);
        APIOutput apiOutput = new APIOutput();

        if (qrCodeRepository.countAllByToAddressIgnoreCase(qrCode.getToAddress()) == 0) {
            qrCode = qrCodeRepository.save(qrCode);
            apiOutput.setData(qrCode);
            emailService.sendEmail(qrCode.getToAddress(), qrCode.getQrCodeUrl());
        } else {
            apiOutput.setStatus(2);
            apiOutput.setMessage("Tài khoản đã tồn tại");
        }
        return apiOutput;
    }

}
