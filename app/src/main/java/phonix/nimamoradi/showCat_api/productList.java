package phonix.nimamoradi.showCat_api;

import com.google.gson.annotations.SerializedName;

public class productList {
    @SerializedName("price")
    String price;
    @SerializedName("percentageOffer")
    String percentageOffer;
    @SerializedName("priceAfterOffer")
    String priceAfterOffer;
    @SerializedName("img")
    String img;
    @SerializedName("star")
    String star;
    @SerializedName("name")
    String name;
    @SerializedName("id")
    String id;
    @SerializedName("catId")
    String catId;

    public productList(String price, String percentageOffer, String priceAfterOffer, String img, String star, String name, String id, String catId) {
        this.price = price;
        this.percentageOffer = percentageOffer;
        this.priceAfterOffer = priceAfterOffer;
        this.img = img;
        this.star = star;
        this.name = name;
        this.id = id;
        this.catId = catId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentageOffer() {
        return percentageOffer;
    }

    public void setPercentageOffer(String percentageOffer) {
        this.percentageOffer = percentageOffer;
    }

    public String getPriceAfterOffer() {
        return priceAfterOffer;
    }

    public void setPriceAfterOffer(String priceAfterOffer) {
        this.priceAfterOffer = priceAfterOffer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
