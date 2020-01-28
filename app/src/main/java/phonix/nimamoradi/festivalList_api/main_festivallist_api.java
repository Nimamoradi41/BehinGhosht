package phonix.nimamoradi.festivalList_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import phonix.nimamoradi.mainPage_api.festival;

public class main_festivallist_api {
    @SerializedName("error")
    Boolean error;
    @SerializedName("festival")
    ArrayList<phonix.nimamoradi.mainPage_api.festival> festivals;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<phonix.nimamoradi.mainPage_api.festival> getFestivals() {
        return festivals;
    }

    public void setFestivals(ArrayList<festival> festivals) {
        this.festivals = festivals;
    }
}
