package phonix.nimamoradi.mainPage_api;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import phonix.nimamoradi.Abstruc_Class.Abstruc_Cate;
import phonix.nimamoradi.Abstruc_Class.Abstruc_Item_Normal;
import phonix.nimamoradi.Abstruc_Class.Info_Item_Normal;

public class main_mainPage_api implements Serializable {
    @SerializedName("error")
    Boolean error;
    @SerializedName("slider")
    ArrayList<slider> sliders;
    @SerializedName("specialOffer")
    ArrayList<specialOffer> specialOffer;
    @SerializedName("cat")
    ArrayList<cat> cat;
    @SerializedName("festival")
    ArrayList<festival> festival;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<slider> getSliders() {
        return sliders;
    }

    public void setSliders(ArrayList<slider> sliders) {
        this.sliders = sliders;
    }

    public ArrayList<phonix.nimamoradi.mainPage_api.specialOffer> getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(ArrayList<phonix.nimamoradi.mainPage_api.specialOffer> specialOffer) {
        this.specialOffer = specialOffer;
    }

    public ArrayList<phonix.nimamoradi.mainPage_api.cat> getCat() {
        return cat;
    }

    public void setCat(ArrayList<phonix.nimamoradi.mainPage_api.cat> cat) {
        this.cat = cat;
    }

    public ArrayList<festival> getFestival() {
        return festival;
    }

    public void setFestival(ArrayList<festival> festival) {
        this.festival = festival;
    }
}
