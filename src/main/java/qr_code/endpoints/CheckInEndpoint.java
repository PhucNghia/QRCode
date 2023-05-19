package qr_code.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qr_code.common.APIOutput;
import qr_code.common.ObjectMapperUtil;
import qr_code.models.QRCode;
import qr_code.services.CheckInService;

@RestController
@RequestMapping(value = "/api/check-in")
public class CheckInEndpoint {
    private final Logger logger = LoggerFactory.getLogger(CheckInEndpoint.class);
    private final CheckInService checkInService;

    public CheckInEndpoint(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping(path = "/create")
    public APIOutput create(@RequestBody QRCode qrCode) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(qrCode));
        APIOutput apiOutput = new APIOutput();
        apiOutput.setData(checkInService.create(qrCode));
        return apiOutput;
    }
}
