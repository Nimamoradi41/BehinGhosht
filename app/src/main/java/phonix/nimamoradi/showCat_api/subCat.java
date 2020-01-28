package phonix.nimamoradi.showCat_api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class subCat implements Serializable {
    @SerializedName("name")
    String name;
    @SerializedName("id")
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
