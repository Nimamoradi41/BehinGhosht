package phonix.nimamoradi;

public class modeldata_sabadkharid {
    String  id;
    String  ima;
    String price;
    String count;
    String off;
    String name;
    String total;

    public modeldata_sabadkharid(String id, String ima, String price, String count, String off,String name,String total) {
        this.name=name;
        this.total=total;
        this.id = id;
        this.ima = ima;
        this.price = price;
        this.count = count;
        this.off = off;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }
}
