package qr_code.models.response;

import qr_code.models.model.Config;
import qr_code.repository.ConfigRepository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ConfigResponse {
    private String htmlConfig;

    public ConfigResponse(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }

    public ConfigResponse() {
    }

    public String getHtmlConfig() {
        return htmlConfig;
    }

    public void setHtmlConfig(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }

    public static ConfigResponse fromModel(Config config){
        return new ConfigResponse(config.getHtmlConfig());
    }
}
