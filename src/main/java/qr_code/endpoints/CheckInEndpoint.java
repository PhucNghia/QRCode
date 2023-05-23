package qr_code.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import qr_code.common.APIOutput;
import qr_code.common.ObjectMapperUtil;
import qr_code.models.dto.ConfigDTO;
import qr_code.models.dto.QRCodeDTO;
import qr_code.models.model.Config;
import qr_code.models.response.ConfigResponse;
import qr_code.services.ConfigService;
import qr_code.services.CreateFormService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/check-in")
public class CheckInEndpoint {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final CreateFormService checkInService;
    private final ConfigService configService;

    public CheckInEndpoint(CreateFormService checkInService, ConfigService configService) {
        this.checkInService = checkInService;
        this.configService = configService;
    }

    //Token dev
    @PostMapping(path = "/create")
    public APIOutput create(@RequestBody QRCodeDTO qrCode) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(qrCode));
        return checkInService.create(QRCodeDTO.fromDTO(qrCode));
    }

    @PostMapping(path = "/config")
    public APIOutput saveConfig(@RequestBody ConfigDTO configRequest, HttpServletRequest request) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(configRequest));
        APIOutput apiOutput = new APIOutput();
        Config config = configService.saveConfig(ConfigDTO.fromDTO(configRequest), request);
        if (config == null) {
            apiOutput.setStatus(0);
        } else {
            apiOutput.setData(ConfigResponse.fromModel(config));
        }
        return apiOutput;
    }

    @GetMapping(path = "/get/config")
    public APIOutput getConfig() {
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(ConfigResponse.fromModel(configService.getLastConfig()));
        return apiOutput;
    }
}
