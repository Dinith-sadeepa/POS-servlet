package webapp.DAO;

import webapp.entity.OrderDetails;

import java.sql.Connection;

public interface OrderDetailsDAO {
    boolean placeOrderDetails(OrderDetails orderDetails, Connection connection);
}
