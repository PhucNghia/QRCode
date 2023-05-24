package qr_code.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import qr_code.models.dto.CheckinDTO;
import qr_code.models.model.QRCode;
import qr_code.models.response.CheckinResponse;
import qr_code.repository.QRCodeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CheckInService {
    private final QRCodeRepository qrCodeRepository;

    @Value("${private_key.token_dev:./}")
    private String tokenDev;

    public CheckInService(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public CheckinResponse checkinResponse(CheckinDTO checkinDTO, HttpServletRequest request) {
        String tokenDeHeader = request.getHeader("token_dev");
        if (tokenDeHeader == null || tokenDeHeader.equals(tokenDev)) {
            Optional<QRCode> qrCode = qrCodeRepository.findByQrCodeUrl(checkinDTO.getQrCodeUrl());
            if (qrCode.isPresent()) {
                QRCode qrCode1 = qrCode.get();
                if (qrCode1.getCheckIn() == null || !qrCode1.getCheckIn()){
                    qrCode1.setCheckIn(true);
                    qrCodeRepository.save(qrCode1);
                    return new CheckinResponse(qrCode1.getToAddress(), qrCode1.getQrCodeUrl());
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
