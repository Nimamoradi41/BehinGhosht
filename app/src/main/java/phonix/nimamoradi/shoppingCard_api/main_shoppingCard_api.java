package phonix.nimamoradi.shoppingCard_api;

import com.google.gson.annotations.SerializedName;

public class main_shoppingCard_api {
    @SerializedName("error")
    Boolean error;
    @SerializedName("gustId")
    String gustId;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getGustId() {
        return gustId;
    }

    public void setGustId(String gustId) {
        this.gustId = gustId;
    }
}
