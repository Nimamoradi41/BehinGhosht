package phonix.nimamoradi.showProduct_api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class main_showProduct_api {
    @SerializedName("error")
    Boolean error;
    @SerializedName("detail")
    String detail;
    @SerializedName("fav")
    Boolean fav ;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("offer")
    String offer;
    @SerializedName("catName")
    String catName;
    @SerializedName("brandName")
    String brandName;
    @SerializedName("attr")
    ArrayList<attr> attr;
    @SerializedName("productLike")
    ArrayList<productLike> productLike;
    @SerializedName("img")
    ArrayList<img> img;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public ArrayList<phonix.nimamoradi.showProduct_api.attr> getAttr() {
        return attr;
    }

    public void setAttr(ArrayList<phonix.nimamoradi.showProduct_api.attr> attr) {
        this.attr = attr;
    }

    public ArrayList<phonix.nimamoradi.showProduct_api.productLike> getProductLike() {
        return productLike;
    }

    public void setProductLike(ArrayList<phonix.nimamoradi.showProduct_api.productLike> productLike) {
        this.productLike = productLike;
    }

    public ArrayList<img> getImg() {
        return img;
    }

    public void setImg(ArrayList<img> img) {
        this.img = img;
    }
}
