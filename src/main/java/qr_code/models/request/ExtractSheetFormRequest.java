package qr_code.models.request;

import qr_code.models.model.FormModel;

import java.io.Serializable;
import java.util.List;

public class ExtractSheetFormRequest implements Serializable {
    private String action;
    private List<FormModel> data;

    public ExtractSheetFormRequest(String action, List<FormModel> data) {
        this.action = action;
        this.data = data;
    }

    public ExtractSheetFormRequest() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<FormModel> getData() {
        return data;
    }

    public void setData(List<FormModel> data) {
        this.data = data;
    }
}
