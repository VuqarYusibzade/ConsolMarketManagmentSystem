package az.code.model;


public class PurchaseItem {

    private int productCode;
    private String name;

    private int count;

    public PurchaseItem(int productCode, String name, int count) {
        this.productCode = productCode;
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
