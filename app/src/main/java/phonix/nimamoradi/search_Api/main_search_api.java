package phonix.nimamoradi.search_Api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import phonix.nimamoradi.mainPage_api.cat;

public class main_search_api implements Serializable {
    @SerializedName("error")
    Boolean error;
    @SerializedName("cat")
    ArrayList<cat> cats;
    @SerializedName("product")
    ArrayList<product> product;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<cat> getCats() {
        return cats;
    }

    public void setCats(ArrayList<cat> cats) {
        this.cats = cats;
    }

    public ArrayList<phonix.nimamoradi.search_Api.product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<phonix.nimamoradi.search_Api.product> product) {
        this.product = product;
    }
}
