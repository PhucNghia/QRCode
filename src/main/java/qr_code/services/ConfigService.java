package qr_code.services;

import org.springframework.stereotype.Service;
import qr_code.models.model.Config;
import qr_code.repository.ConfigRepository;

import java.util.List;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public Config saveConfig(Config config) {
        configRepository.save(config);
        return config;
    }

    public Config getLastConfig() {
        List<Config> listConfig = configRepository.findAll();
        if (listConfig.isEmpty()) return null;
        return listConfig.get(listConfig.size() - 1);
    }
}
