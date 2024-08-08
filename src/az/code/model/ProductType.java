package az.code.model;

public enum ProductType {
    FOOD,
    DRINK,
    SWEET,
    MEAT;

private int productTyp;
  void productType1(int productTyp){
        this.productTyp=productTyp;
    }

    public int getProductTyp() {
        return productTyp;
    }
}
