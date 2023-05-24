package qr_code.models.request;

import qr_code.models.model.QRCode;

import java.io.Serializable;
import java.util.List;

public class ExtractQRCodeModel implements Serializable {
    private String action;
    private List<QRCode> data;

    public ExtractQRCodeModel(String action, List<QRCode> data) {
        this.action = action;
        this.data = data;
    }

    public ExtractQRCodeModel() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<QRCode> getData() {
        return data;
    }

    public void setData(List<QRCode> data) {
        this.data = data;
    }
}
