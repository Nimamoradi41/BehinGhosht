package phonix.nimamoradi.showSubCat_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class main_showSubCat_api {
  @SerializedName("error")
    Boolean error;
  @SerializedName("productList")
    ArrayList<productList_subcat> productList;
    @SerializedName("MSG")
    String   MSg;
    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<productList_subcat> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<productList_subcat> productList) {
        this.productList = productList;
    }

    public String getMSg() {
        return MSg;
    }

    public void setMSg(String MSg) {
        this.MSg = MSg;
    }
}
