package webapp.BO.impl;

import webapp.BO.OrdersBO;
import webapp.DAO.ItemDAO;
import webapp.DAO.OrderDetailsDAO;
import webapp.DAO.OrdersDAO;
import webapp.DAO.impl.ItemDAOImpl;
import webapp.DAO.impl.OrderDetailsDAOImpl;
import webapp.DAO.impl.OrdersDAOImpl;
import webapp.dto.OrderDetailsDTO;
import webapp.dto.OrdersDTO;
import webapp.entity.OrderDetails;
import webapp.entity.Orders;
import webapp.listener.ContextListener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    private OrdersDAO ordersDAO;
    private OrderDetailsDAO orderDetailsDAO;
    private ItemDAO itemDAO;


    public OrdersBOImpl() throws SQLException {

        ordersDAO = new OrdersDAOImpl();
        orderDetailsDAO = new OrderDetailsDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO) {

        DataSource pool = null;
        Connection connection = null;

        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
//            System.out.println(connection);
            boolean isOrdered = ordersDAO.placeOrder(new Orders(ordersDTO.getOrderId(), ordersDTO.getOrderDate(),
                    ordersDTO.getCustomerDTO().getCusId()), connection);
            if (isOrdered) {
                List<OrderDetailsDTO> orderDetailsDTOS = ordersDTO.getOrderDetailsDTOS();
                for (int i = 0; i < orderDetailsDTOS.size(); i++) {
                    boolean isOrderDetailsPlaced = orderDetailsDAO.placeOrderDetails(
                            new OrderDetails(orderDetailsDTOS.get(i).getOrderId(),
                                    orderDetailsDTOS.get(i).getItemCode(),
                                    orderDetailsDTOS.get(i).getOrderQty(),
                                    orderDetailsDTOS.get(i).getOrderPrice()), connection);
                    if (isOrderDetailsPlaced) {
                        boolean isUpdateQty = itemDAO.updateItemQty(orderDetailsDTOS.get(i).getItemCode(),
                                orderDetailsDTOS.get(i).getOrderQty(), connection);
                        if (!isUpdateQty) {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            } else {
                connection.rollback();
                return false;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
