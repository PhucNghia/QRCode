package qr_code.models.response;

import qr_code.models.model.FormModel;

public class FormEventResponse {
    private Long id;
    private String toAddress;
    private String content;
    private Integer experience;
    private String level;
    private String optional;
    private String qrCodeUrl;
    private Boolean isCheckIn;
    private Long createdTime;

    public FormEventResponse() {
    }

    public FormEventResponse(Long id, String toAddress, String content, Integer experience, String level, String optional, String qrCodeUrl, Boolean isCheckIn, Long createdTime) {
        this.id = id;
        this.toAddress = toAddress;
        this.content = content;
        this.experience = experience;
        this.level = level;
        this.optional = optional;
        this.qrCodeUrl = qrCodeUrl;
        this.isCheckIn = isCheckIn;
        this.createdTime = createdTime;
    }

    public static FormEventResponse fromModel(FormModel formModel) {
        return new FormEventResponse(formModel.getId(), formModel.getToAddress(), formModel.getContent(), formModel.getExperience(), formModel.getLevel(), formModel.getOptional(), formModel.getQrCodeUrl(), formModel.getCheckIn(), formModel.getCreatedTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Boolean getCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(Boolean checkIn) {
        isCheckIn = checkIn;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
