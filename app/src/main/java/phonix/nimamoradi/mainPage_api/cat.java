package phonix.nimamoradi.mainPage_api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class cat implements Serializable {
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String img;
    @SerializedName("id")
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
