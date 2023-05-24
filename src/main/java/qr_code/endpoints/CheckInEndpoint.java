package qr_code.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import qr_code.common.APIOutput;
import qr_code.common.ObjectMapperUtil;
import qr_code.models.dto.CheckinDTO;
import qr_code.models.dto.ConfigDTO;
import qr_code.models.dto.QRCodeDTO;
import qr_code.models.model.Config;
import qr_code.models.response.CheckinResponse;
import qr_code.models.response.ConfigResponse;
import qr_code.services.CheckInService;
import qr_code.services.ConfigService;
import qr_code.services.CreateFormService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/check-in")
public class CheckInEndpoint {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final CreateFormService createFormService;
    private final ConfigService configService;

    private final CheckInService checkInService;

    public CheckInEndpoint(CreateFormService createFormService, ConfigService configService, CheckInService checkInService) {
        this.createFormService = createFormService;
        this.configService = configService;
        this.checkInService = checkInService;
    }

    //Token dev
    @PostMapping(path = "/create")
    public APIOutput create(@RequestBody QRCodeDTO qrCode) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(qrCode));
        return createFormService.create(QRCodeDTO.fromDTO(qrCode));
    }

    @PostMapping(path = "/config")
    public APIOutput saveConfig(@RequestBody ConfigDTO configRequest, HttpServletRequest request) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(configRequest));
        APIOutput apiOutput = new APIOutput();
        Config config = configService.saveConfig(ConfigDTO.fromDTO(configRequest), request);
        apiOutput.setData(ConfigResponse.fromModel(config));
        return apiOutput;
    }

    @GetMapping(path = "/get/config")
    public APIOutput getConfig() {
        APIOutput apiOutput = new APIOutput();
        configService.sendDataToSheet();
        apiOutput.setData(ConfigResponse.fromModel(configService.getLastConfig()));
        return apiOutput;
    }

    @PostMapping(path = "/checkUser")
    public APIOutput getOutput(@RequestBody CheckinDTO checkinDTO, HttpServletRequest request) {
        APIOutput apiOutput = new APIOutput();
        CheckinResponse checkinResponse = checkInService.checkinResponse(checkinDTO, request);
        apiOutput.setData(checkinResponse);
        return apiOutput;
    }
}
