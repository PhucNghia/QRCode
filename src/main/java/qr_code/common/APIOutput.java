package qr_code.common;

public class APIOutput {
    private Integer status;
    private Object data;

    private String message;

    public APIOutput() {
        this.status = 1;
        this.message = null;
    }

    public APIOutput(String message) {
        this.status = 2;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
