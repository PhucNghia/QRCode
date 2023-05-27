package qr_code.models.request;

public class CheckInRequest {
    private String qrCodeUrl;

    public CheckInRequest(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
