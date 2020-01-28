package phonix.nimamoradi.festivalList_api;

import com.google.gson.annotations.SerializedName;

public class festival {
    @SerializedName("id")
    boolean id;
    @SerializedName("Percentage")
    boolean Percentage;
    @SerializedName("img")
    boolean img;

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isPercentage() {
        return Percentage;
    }

    public void setPercentage(boolean percentage) {
        Percentage = percentage;
    }

    public boolean isImg() {
        return img;
    }

    public void setImg(boolean img) {
        this.img = img;
    }
}
