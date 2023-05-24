package qr_code.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import qr_code.common.ObjectMapperUtil;
import qr_code.common.RequestUtils;
import qr_code.models.model.Config;
import qr_code.models.model.QRCode;
import qr_code.models.request.ExtractQRCodeModel;
import qr_code.repository.ConfigRepository;
import qr_code.repository.QRCodeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ConfigService {

    private static final String URL_APP_SCRIPT = "https://script.google.com/macros/s/AKfycbw_5Ttl8iORSmL3fHjexB54N1gQwp1Lt367DBUJOwnNTiAOZyJ3uh-eM6mTZ6T0Njcc/exec";
    private static final String POST_ACTION_SAVE_QR = "POST_ACTION_SAVE_QR";
    private final ConfigRepository configRepository;

    private final QRCodeRepository qrCodeRepository;

    private final TaskExecutor taskExecutor;
    @Value("${private_key.token_dev:./}")
    private String tokenDev;

    public ConfigService(ConfigRepository configRepository, QRCodeRepository qrCodeRepository, TaskExecutor taskExecutor) {
        this.configRepository = configRepository;
        this.qrCodeRepository = qrCodeRepository;
        this.taskExecutor = taskExecutor;
    }

    public Config saveConfig(Config config, HttpServletRequest request) {
        String tokenDeHeader = request.getHeader("token_dev");
        if (tokenDeHeader != null && tokenDeHeader.equals(tokenDev)) {
            configRepository.save(config);
            return config;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void sendDataToSheet() {
        taskExecutor.execute(() -> {
            List<QRCode> listQRCode = qrCodeRepository.findAll();
            if (listQRCode.isEmpty()) {
                return;
            }
            ExtractQRCodeModel extractQRCodeModel = new ExtractQRCodeModel(POST_ACTION_SAVE_QR,listQRCode);
            String jsonData = ObjectMapperUtil.toJsonString(extractQRCodeModel);
            RequestUtils.executeRequest(URL_APP_SCRIPT, "POST", "",jsonData);
        });
    }



    public Config getLastConfig() {
        List<Config> listConfig = configRepository.findAll();
        if (listConfig.isEmpty()) return null;
        return listConfig.get(listConfig.size() - 1);
    }
}
