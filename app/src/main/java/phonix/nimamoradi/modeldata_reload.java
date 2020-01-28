package phonix.nimamoradi;

import org.json.JSONObject;

public class modeldata_reload {
    JSONObject object;
    String string;

    public modeldata_reload(JSONObject object, String string) {
        this.object = object;
        this.string = string;
    }

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
