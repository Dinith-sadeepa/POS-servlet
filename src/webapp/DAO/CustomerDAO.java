package webapp.DAO;

import webapp.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    boolean createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String cusId);
}
