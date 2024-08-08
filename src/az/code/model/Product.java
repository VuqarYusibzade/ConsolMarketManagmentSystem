package az.code.model;

public class Product {
    private long code;
    private String name;
    private int count;
    private ProductType productType;
    private double price;

    public Product(String name, double price, ProductType productType, int count, long code) {
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.count = count;
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
