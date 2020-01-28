package phonix.nimamoradi.openFestival_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class main_openfestival_api {
    @SerializedName("error")
    Boolean error;
    @SerializedName("product")
    ArrayList<productList> productList;

    public Boolean getError() {
        return error;
    }
    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<phonix.nimamoradi.openFestival_api.productList> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<phonix.nimamoradi.openFestival_api.productList> productList) {
        this.productList = productList;
    }
}
