package qr_code.models.dto;


import qr_code.models.model.Config;

import java.io.Serializable;

public class ConfigDTO implements Serializable {
    private String htmlConfig;

    private String subject;

    public ConfigDTO(String htmlConfig, String subject) {
        this.htmlConfig = htmlConfig;
        this.subject = subject;
    }

    public ConfigDTO() {
    }

    public static Config fromDTO(ConfigDTO configDTO) {
        return new Config(configDTO.getHtmlConfig(), configDTO.getSubject());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlConfig() {
        return htmlConfig;
    }

    public void setHtmlConfig(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }
}
