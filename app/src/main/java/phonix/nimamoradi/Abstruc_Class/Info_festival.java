package phonix.nimamoradi.Abstruc_Class;

import com.google.gson.annotations.SerializedName;

public class Info_festival {
    @SerializedName("id")
    String id;
    @SerializedName("Percentage")
    String Percentage;
    @SerializedName("img")
    String img;
    @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
