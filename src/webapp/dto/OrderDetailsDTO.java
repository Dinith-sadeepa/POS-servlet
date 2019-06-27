package webapp.dto;

public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private long orderQty;
    private double orderPrice;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderId, String itemCode, long orderQty, double orderPrice) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.orderPrice = orderPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(long orderQty) {
        this.orderQty = orderQty;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
