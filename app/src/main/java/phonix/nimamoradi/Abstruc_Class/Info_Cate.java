package phonix.nimamoradi.Abstruc_Class;

import com.google.gson.annotations.SerializedName;

public class Info_Cate {
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
