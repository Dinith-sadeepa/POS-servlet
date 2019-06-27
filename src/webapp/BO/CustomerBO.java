package webapp.BO;

import webapp.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO {
    boolean createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String cusId);
}
