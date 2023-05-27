package qr_code.models.request;

public class ConfigEventRequest {
    private String htmlConfig;

    private String subject;

    private Integer target;

    public ConfigEventRequest(String htmlConfig, String subject, Integer target) {
        this.htmlConfig = htmlConfig;
        this.subject = subject;
        this.target = target;
    }

    public ConfigEventRequest() {
    }

    public String getHtmlConfig() {
        return htmlConfig;
    }

    public void setHtmlConfig(String htmlConfig) {
        this.htmlConfig = htmlConfig;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }
}

