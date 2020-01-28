package phonix.nimamoradi.showProduct_api;

import com.google.gson.annotations.SerializedName;

public class attr {
    @SerializedName("name")
    String name;
    @SerializedName("value")
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
