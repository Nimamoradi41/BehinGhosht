package phonix.nimamoradi.showProduct_api;

import com.google.gson.annotations.SerializedName;

public class img {
    @SerializedName("img")
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
