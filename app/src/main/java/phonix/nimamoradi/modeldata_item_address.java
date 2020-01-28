package phonix.nimamoradi;

public class modeldata_item_address {
    String id;
    String address;
    String city;
    String province;
    String postCode;
    public boolean Flg;

    public boolean isFlg() {
        return Flg;
    }

    public void setFlg(boolean flg) {
        Flg = flg;
    }

    public modeldata_item_address(String id, String address, String city, String province, String postCode, Boolean Flag) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postCode = postCode;
        this.Flg=Flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
