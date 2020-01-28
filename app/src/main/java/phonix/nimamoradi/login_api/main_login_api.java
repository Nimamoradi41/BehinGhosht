package phonix.nimamoradi.login_api;

import com.google.gson.annotations.SerializedName;

public class main_login_api {
    @SerializedName("error")
    Boolean error ;
    @SerializedName("MSG")
    String MSG;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }
}
