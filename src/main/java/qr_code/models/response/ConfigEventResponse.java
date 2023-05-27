package qr_code.models.response;

import qr_code.models.model.Config;

public class ConfigEventResponse {
    private String htmlConfig;

    private String subject;

    private Integer target;

    public ConfigEventResponse(String htmlConfig, String subject, Integer target) {
        this.htmlConfig = htmlConfig;
        this.subject = subject;
        this.target = target;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public ConfigEventResponse() {
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


    public static ConfigEventResponse fromModel(Config config) {
        return new ConfigEventResponse(config.getHtmlConfig(), config.getSubject(),config.getTarget());
    }
}
