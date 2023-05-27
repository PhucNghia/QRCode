package qr_code.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import qr_code.common.APIOutput;
import qr_code.common.Constant;
import qr_code.common.ObjectMapperUtil;
import qr_code.models.request.CheckInRequest;
import qr_code.models.request.ConfigEventRequest;
import qr_code.models.request.FormEventRequest;
import qr_code.services.CheckInService;
import qr_code.services.ConfigService;
import qr_code.services.CreateFormService;

import javax.servlet.http.HttpServletRequest;

import static qr_code.common.Constant.API_EXTRACT_TO_SHEET;

@RestController
@RequestMapping(value = Constant.END_POINT_CHECKIN)
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
    @PostMapping(path = Constant.API_CREATE_FORM)
    public APIOutput create(@RequestBody FormEventRequest eventRequest) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(eventRequest));
        return createFormService.saveForm(eventRequest);
    }

    @PostMapping(path = Constant.API_CREATE_CONFIG)
    public APIOutput saveConfig(@RequestBody ConfigEventRequest eventRequest, HttpServletRequest request) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(eventRequest));
        return configService.saveConfig(eventRequest, request);
    }

    @GetMapping(path = Constant.API_GET_CONFIG)
    public APIOutput getConfig(HttpServletRequest request) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(request));
        return configService.getConfig(request);
    }

    @PostMapping(path = Constant.API_CHECKIN)
    public APIOutput getOutput(@RequestBody CheckInRequest checkInRequest, HttpServletRequest request) {
        logger.info("#{}", ObjectMapperUtil.toJsonString(checkInRequest));
        return checkInService.checkinResponse(checkInRequest, request);
    }

    @GetMapping(path = API_EXTRACT_TO_SHEET)
    public APIOutput extractToSheet(HttpServletRequest httpServletRequest){
        APIOutput apiOutput = new APIOutput();
        configService.sendDataToSheet(httpServletRequest);
        return apiOutput;
    }

}
