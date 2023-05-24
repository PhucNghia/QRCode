package qr_code.models.dto;

import qr_code.models.model.QRCode;

import java.io.Serializable;

public class QRCodeDTO implements Serializable {
    private Long id;
    private String toAddress;
    private String content;
    private Integer experience;
    private String level;
    private String optional;
    private String qrCodeUrl;
    private Boolean isCheckIn;

    public QRCodeDTO() {
    }

    public static QRCode fromDTO(QRCodeDTO qrCodeDTO) {
        return new QRCode(qrCodeDTO.getId(), qrCodeDTO.getToAddress(), qrCodeDTO.getContent(), qrCodeDTO.getExperience(), qrCodeDTO.getLevel(), qrCodeDTO.getOptional(), qrCodeDTO.getQrCodeUrl(), qrCodeDTO.getIsCheckIn());
    }

    public QRCodeDTO(Long id, String toAddress, String content, Integer experience, String level, String optional, String qrCodeUrl, Boolean isCheckIn) {
        this.id = id;
        this.toAddress = toAddress;
        this.content = content;
        this.experience = experience;
        this.level = level;
        this.optional = optional;
        this.qrCodeUrl = qrCodeUrl;
        this.isCheckIn = isCheckIn;
    }

    public Boolean getIsCheckIn() {
        return isCheckIn;
    }

    public void setIsCheckIn(Boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
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
}
