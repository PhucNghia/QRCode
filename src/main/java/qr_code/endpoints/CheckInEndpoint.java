package qr_code.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import qr_code.common.APIOutput;
import qr_code.common.ObjectMapperUtil;
import qr_code.models.dto.ConfigDTO;
import qr_code.models.dto.QRCodeDTO;
import qr_code.services.ConfigService;
import qr_code.services.CreateFormService;

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

    @PostMapping(path = "/create")
    public APIOutput create(@RequestBody QRCodeDTO qrCode) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(qrCode));
        return checkInService.create(QRCodeDTO.fromDTO(qrCode));
    }

    @PostMapping(path = "/config")
    public APIOutput saveConfig(@RequestBody ConfigDTO config) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(config));
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(configService.saveConfig(ConfigDTO.fromDTO(config)));
        return apiOutput;
    }

    @GetMapping(path = "/get/config")
    public APIOutput getConfig(){
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(configService.getLastConfig());
        return (apiOutput);
    }
}
