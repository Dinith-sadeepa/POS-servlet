package webapp.BO;

import webapp.dto.OrdersDTO;

public interface OrdersBO {
    boolean placeOrder(OrdersDTO ordersDTO);
}
