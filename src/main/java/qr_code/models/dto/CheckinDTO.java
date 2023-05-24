package qr_code.models.dto;

public class CheckinDTO {
    private String qrCodeUrl;

    public CheckinDTO(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public CheckinDTO() {
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
