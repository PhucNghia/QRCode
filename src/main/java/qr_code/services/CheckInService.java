package qr_code.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import qr_code.common.APIOutput;
import qr_code.models.model.FormModel;
import qr_code.models.request.CheckInRequest;
import qr_code.models.response.CheckinResponse;
import qr_code.repository.FormRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CheckInService {
    private final FormRepository formRepository;

    @Value("${private_key.token_dev:./}")
    private String tokenDev;

    public CheckInService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public APIOutput checkinResponse(CheckInRequest checkInRequest, HttpServletRequest request) {
        String tokenDeHeader = request.getHeader("token_dev");
        if (tokenDeHeader == null || tokenDeHeader.equals(tokenDev)) {
            APIOutput apiOutput = new APIOutput();
            Optional<FormModel> qrCode = formRepository.findByQrCodeUrl(checkInRequest.getQrCodeUrl());
            if (qrCode.isPresent()) {
                FormModel formModel1 = qrCode.get();
                if (formModel1.getCheckIn() == null || !formModel1.getCheckIn()) {
                    formModel1.setCheckIn(true);
                    formRepository.save(formModel1);
                    CheckinResponse checkinResponse = new CheckinResponse(formModel1.getToAddress(), formModel1.getQrCodeUrl());
                    apiOutput.setData(checkinResponse);
                }
            } else {
                apiOutput.setStatus(2);
                apiOutput.setMessage("Không tồn tại tài khoản trong hệ thống");
            }
            return apiOutput;

        }
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
