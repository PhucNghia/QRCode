package qr_code.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import qr_code.common.APIOutput;
import qr_code.endpoints.CheckInEndpoint;
import qr_code.models.model.FormModel;
import qr_code.models.request.FormEventRequest;
import qr_code.models.response.FormEventResponse;
import qr_code.repository.FormRepository;

@Service
public class CreateFormService {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final FormRepository formRepository;
    private final QRCodeService qrCodeService;
    private final EmailService emailService;

    public CreateFormService(FormRepository formRepository, QRCodeService qrCodeService, EmailService emailService) {
        this.formRepository = formRepository;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
    }

    public APIOutput saveForm(FormEventRequest eventRequest) {
        if (StringUtils.isEmpty(eventRequest.getToAddress())
                || StringUtils.isEmpty(eventRequest.getContent())
                || StringUtils.isEmpty(eventRequest.getExperience())
                || StringUtils.isEmpty(eventRequest.getLevel())
                || StringUtils.isEmpty(eventRequest.getCreatedTime())
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        APIOutput apiOutput = new APIOutput();
        if (formRepository.countAllByToAddressIgnoreCase(eventRequest.getToAddress()) == 0) {
            String qrCodeUrl = qrCodeService.genQRCore(eventRequest.getContent());
            FormModel formModel = new FormModel();
            formModel.setToAddress(eventRequest.getToAddress());
            formModel.setContent(eventRequest.getContent());
            formModel.setLevel(eventRequest.getLevel());
            formModel.setOptional(eventRequest.getOptional());
            formModel.setQrCodeUrl(qrCodeUrl);
            formModel.setCheckIn(false);
            formModel.setCreatedTime(eventRequest.getCreatedTime());
            formRepository.save(formModel);
            emailService.sendEmail(formModel.getToAddress(),formModel.getToAddress());
            FormEventResponse formEventResponse = FormEventResponse.fromModel(formModel);
            apiOutput.setData(formEventResponse);
        } else {
            apiOutput.setStatus(2);
            apiOutput.setMessage("Tài khoản đã tồn tại");
        }
        return apiOutput;

    }

}
