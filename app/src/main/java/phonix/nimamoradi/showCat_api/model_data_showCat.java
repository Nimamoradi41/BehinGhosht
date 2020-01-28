package phonix.nimamoradi.showCat_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class model_data_showCat {
    @SerializedName("error")
    Boolean error;
    @SerializedName("productList")
    ArrayList<productList> productList;
    @SerializedName("subCat")
    ArrayList<subCat> subCat;
    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<phonix.nimamoradi.showCat_api.productList> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<phonix.nimamoradi.showCat_api.productList> productList) {
        this.productList = productList;
    }

    public ArrayList<phonix.nimamoradi.showCat_api.subCat> getSubCat() {
        return subCat;
    }

    public void setSubCat(ArrayList<phonix.nimamoradi.showCat_api.subCat> subCat) {
        this.subCat = subCat;
    }
}
