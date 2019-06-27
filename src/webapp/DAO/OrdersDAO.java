package webapp.DAO;

import webapp.entity.Orders;

import java.sql.Connection;

public interface OrdersDAO {
    boolean placeOrder(Orders orders, Connection connection);
}
