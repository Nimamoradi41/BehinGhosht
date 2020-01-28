package phonix.nimamoradi.mainPage_api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class slider implements Serializable {
    @SerializedName("imageAddress")
    String imageAddress;

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
