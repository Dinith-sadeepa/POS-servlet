package webapp.dto;

public class ItemDTO {
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double itemPrice;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String itemName, int itemQty, double itemPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty=" + itemQty +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
