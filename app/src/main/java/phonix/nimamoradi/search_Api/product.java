package phonix.nimamoradi.search_Api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class product  implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String img;
    @SerializedName("percentageOffer")
    String percentageOffer;
    @SerializedName("priceAfterOffer")
    String priceAfterOffer;
    @SerializedName("star")
    String star;
    @SerializedName("price")
    String price;

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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
