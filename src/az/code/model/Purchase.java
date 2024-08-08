package az.code.model;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {
    private int id;
    private List<PurchaseItem> purchaseItems;
    private LocalDateTime creationDate;
    private double totalPrice;

    public Purchase(int id, List<PurchaseItem> purchaseItems, LocalDateTime creationDate, double totalPrice) {
        this.id = id;
        this.purchaseItems = purchaseItems;
        this.creationDate = creationDate;
        this.totalPrice = totalPrice;
    }



    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
