package phonix.nimamoradi;

public class modeldata_citys {
    String id;
    String name;
    String id_ostan;

    public modeldata_citys(String id, String name, String id_ostan) {
        this.id = id;
        this.name = name;
        this.id_ostan = id_ostan;
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

    public String getId_ostan() {
        return id_ostan;
    }

    public void setId_ostan(String id_ostan) {
        this.id_ostan = id_ostan;
    }
}
