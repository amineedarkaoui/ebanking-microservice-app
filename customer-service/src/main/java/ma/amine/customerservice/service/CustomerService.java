package ma.amine.customerservice.service;

import lombok.RequiredArgsConstructor;
import ma.amine.customerservice.entity.Customer;
import ma.amine.customerservice.repository.CustomerRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find a customer by ID")
    public Customer findCustomerById(
            @McpToolParam(description = "The customer ID") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @McpTool(description = "Save a new customer")
    public Customer saveCustomer(
            @McpToolParam(description = "The customer to save (name, email)") Customer customer) {
        return customerRepository.save(customer);
    }
}
