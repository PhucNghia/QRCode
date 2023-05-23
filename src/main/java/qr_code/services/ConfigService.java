package qr_code.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qr_code.models.model.Config;
import qr_code.repository.ConfigRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;
    @Value("${private_key.token_dev:./}")
    private String tokenDev;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public Config saveConfig(Config config, HttpServletRequest request) {
        String tokenDeHeader = request.getHeader("token_dev");
        if (tokenDeHeader != null && tokenDeHeader.equals(tokenDev)) {
            configRepository.save(config);
            return config;
        } else {
            return null;
        }
    }

    public Config getLastConfig() {
        List<Config> listConfig = configRepository.findAll();
        if (listConfig.isEmpty()) return null;
        return listConfig.get(listConfig.size() - 1);
    }
}
