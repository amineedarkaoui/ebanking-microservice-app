package ma.amine.ebankservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.amine.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id, Exception e) {
        return Customer.builder()
                .id(id)
                .name("Not available")
                .email("Not available")
                .build();
    }
}
