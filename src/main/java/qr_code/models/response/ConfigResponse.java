package qr_code.models.response;

import qr_code.models.model.Config;

public class ConfigResponse {
    private String htmlConfig;

    private String subject;

    public ConfigResponse(String htmlConfig, String subject) {
        this.htmlConfig = htmlConfig;
        this.subject = subject;
    }

    public ConfigResponse() {
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

    public static ConfigResponse fromModel(Config config) {
        return new ConfigResponse(config.getHtmlConfig(), config.getSubject());
    }
}
