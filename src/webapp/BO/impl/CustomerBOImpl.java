package webapp.BO.impl;

import webapp.BO.CustomerBO;
import webapp.DAO.CustomerDAO;
import webapp.DAO.impl.CustomerDAOImpl;
import webapp.dto.CustomerDTO;
import webapp.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
        customerDAO = new CustomerDAOImpl();
    }

    @Override
    public boolean createCustomer(CustomerDTO customerDTO) {
        return customerDAO.createCustomer(new Customer(customerDTO.getCusId(),
                customerDTO.getCusName(), customerDTO.getCusAddress()));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        if (!allCustomers.isEmpty() || allCustomers != null) {
            allCustomers.forEach(customer -> {
                customerDTOS.add(new CustomerDTO(customer.getCusId(), customer.getCusName(), customer.getCusAddress()));
            });
        }
        return customerDTOS;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerDAO.updateCustomer(new Customer(customerDTO.getCusId(),
                customerDTO.getCusName(), customerDTO.getCusAddress()));
    }

    @Override
    public boolean deleteCustomer(String cusId) {
        return customerDAO.deleteCustomer(cusId);
    }
}
