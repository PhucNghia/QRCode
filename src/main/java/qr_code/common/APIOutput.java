package qr_code.common;

public class APIOutput {
    private Integer status;
    private Object data;

    public APIOutput() {
        this.status = 1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
