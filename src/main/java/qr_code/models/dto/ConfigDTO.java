package qr_code.models.dto;


import qr_code.models.model.Config;

import java.io.Serializable;

public class ConfigDTO implements Serializable {
    private String htmlConfig;

    public ConfigDTO(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }

    public ConfigDTO() {
    }

    public static Config fromDTO(ConfigDTO configDTO) {
        return new Config(configDTO.getHtmlConfig());
    }

    public String getHtmlConfig() {
        return htmlConfig;
    }

    public void setHtmlConfig(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }
}
