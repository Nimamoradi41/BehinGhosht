package phonix.nimamoradi;

import java.io.Serializable;

public class model_data_search_resuilt_cate implements Serializable {
    String   id;
    String   name;
    String   img;

    public model_data_search_resuilt_cate(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
