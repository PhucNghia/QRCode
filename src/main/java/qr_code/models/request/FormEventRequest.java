package qr_code.models.request;

// Model nhận request từ client để lưu form
public class FormEventRequest {
    private String toAddress;
    private String content;
    private Integer experience;
    private String level;
    private String optional;
    private Long createdTime;

    public FormEventRequest(String toAddress, String content, Integer experience, String level, String optional, Long createdTime) {
        this.toAddress = toAddress;
        this.content = content;
        this.experience = experience;
        this.level = level;
        this.optional = optional;
        this.createdTime = createdTime;
    }

    public FormEventRequest() {
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
