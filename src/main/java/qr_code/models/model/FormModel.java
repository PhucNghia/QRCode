package qr_code.models.model;

import javax.persistence.*;

@Entity
@Table(name = "qr_code_info")
public class FormModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "toAddress")
    private String toAddress;
    private String content;
    private Integer experience;
    private String level;
    private String optional;
    @Column(length = 12000)
    private String qrCodeUrl;

    private Boolean isCheckIn;
    private Long createdTime;

    public FormModel(String toAddress, String content, Integer experience, String level, String optional, String qrCodeUrl, Boolean isCheckIn, Long createdTime) {
        this.toAddress = toAddress;
        this.content = content;
        this.experience = experience;
        this.level = level;
        this.optional = optional;
        this.qrCodeUrl = qrCodeUrl;
        this.isCheckIn = isCheckIn;
        this.createdTime = createdTime;
    }

    public Boolean getCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(Boolean checkIn) {
        isCheckIn = checkIn;
    }

    public FormModel() {
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

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
