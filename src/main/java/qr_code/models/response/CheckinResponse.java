package qr_code.models.response;

public class CheckinResponse {
    private String toAddress;
    private String qrCodeUrl;

    public CheckinResponse(String toAddress, String qrCodeUrl) {
        this.toAddress = toAddress;
        this.qrCodeUrl = qrCodeUrl;
    }

    public CheckinResponse() {
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }


}
