package qr_code.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import qr_code.common.APIOutput;
import qr_code.common.Constant;
import qr_code.common.ObjectMapperUtil;
import qr_code.common.RequestUtils;
import qr_code.models.model.Config;
import qr_code.models.model.FormModel;
import qr_code.models.request.ConfigEventRequest;
import qr_code.models.request.ExtractSheetFormRequest;
import qr_code.models.response.ConfigEventResponse;
import qr_code.repository.ConfigRepository;
import qr_code.repository.FormRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ConfigService {

    private static final String URL_APP_SCRIPT = "https://script.google.com/macros/s/AKfycbw_5Ttl8iORSmL3fHjexB54N1gQwp1Lt367DBUJOwnNTiAOZyJ3uh-eM6mTZ6T0Njcc/exec";
    private static final String POST_ACTION_SAVE_QR = "POST_ACTION_SAVE_QR";
    private final ConfigRepository configRepository;
    private final FormRepository formRepository;
    private final TaskExecutor taskExecutor;
    @Value("${private_key.token_dev:./}")
    private String tokenDev;

    public ConfigService(ConfigRepository configRepository, FormRepository formRepository, TaskExecutor taskExecutor) {
        this.configRepository = configRepository;
        this.formRepository = formRepository;
        this.taskExecutor = taskExecutor;
    }

    public APIOutput saveConfig(ConfigEventRequest eventRequest, HttpServletRequest request) {
        String tokenDeHeader = request.getHeader(Constant.TOKEN_DEV);
        if (tokenDeHeader != null && tokenDeHeader.equals(tokenDev)) {
            if (StringUtils.isEmpty(eventRequest.getHtmlConfig()) && StringUtils.isEmpty(eventRequest.getSubject()) && eventRequest.getTarget() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            Config config = new Config();
            config.setHtmlConfig(eventRequest.getHtmlConfig());
            config.setSubject(eventRequest.getSubject());
            config.setTarget(eventRequest.getTarget());
            configRepository.save(config);

            ConfigEventResponse configEventResponse = ConfigEventResponse.fromModel(config);
            APIOutput apiOutput = new APIOutput();
            apiOutput.setData(configEventResponse);
            return apiOutput;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void sendDataToSheet(HttpServletRequest request) {
        String tokenDeHeader = request.getHeader(Constant.TOKEN_DEV);
        if (tokenDeHeader != null && tokenDeHeader.equals(tokenDev)) {
            taskExecutor.execute(() -> {
                List<FormModel> listFormModel = formRepository.findAll();
                if (listFormModel.isEmpty()) {
                    return;
                }
                ExtractSheetFormRequest extractSheetFormRequest = new ExtractSheetFormRequest(POST_ACTION_SAVE_QR, listFormModel);
                String jsonData = ObjectMapperUtil.toJsonString(extractSheetFormRequest);
                RequestUtils.executeRequest(URL_APP_SCRIPT, "POST", "", jsonData);
            });
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


    public APIOutput getConfig(HttpServletRequest request) {
        String tokenDeHeader = request.getHeader(Constant.TOKEN_DEV);
        if (tokenDeHeader != null && tokenDeHeader.equals(tokenDev)) {
            APIOutput apiOutput = new APIOutput();
            List<Config> listConfig = configRepository.findAll();
            if (listConfig.isEmpty()) {
                apiOutput.setMessage("Không có config");
            }
            apiOutput.setData(listConfig.get(listConfig.size() - 1));
            return apiOutput;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public Config getLastConfig() {
        List<Config> listConfig = configRepository.findAll();
        if (listConfig.isEmpty()) return null;
        return listConfig.get(listConfig.size() - 1);
    }
}
