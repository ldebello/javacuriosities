package ar.com.javacuriosities.spring.rs.resources;

import ar.com.javacuriosities.spring.rs.model.Customer;
import ar.com.javacuriosities.spring.rs.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public List<Customer> getCustomers() {
        return CustomerRepository.getCustomers();
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public Customer getCustomerById(@PathVariable("id") int id) {
        return CustomerRepository.getCustomer(id);
    }

    @GetMapping(value = "search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public List<Customer> getCustomerByName(@RequestParam("name") String name) {
        return CustomerRepository.getCustomerByName(name);
    }

    @GetMapping(value = "search/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public List<Customer> searchByName(@PathVariable("name") String name) {
        return CustomerRepository.getCustomerByName(name);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"}
    )
    public Customer create(Customer customer) {
        if (customer.getAge() < 18) {
            throw new IllegalArgumentException("Customer cannot be under-age");
        }
        return CustomerRepository.saveCustomer(customer);
    }

    @PutMapping(value = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw new RuntimeException("Id is required");
        }
        return CustomerRepository.updateCustomer(customer);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity remove(@PathVariable("id") int id) {
        CustomerRepository.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "add")
    public ResponseEntity addClient(@RequestParam("name") String name, @RequestParam("age") int age) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        CustomerRepository.saveCustomer(customer);
        return ResponseEntity.status(200).body("Customer created, Name: " + name + ", Age: " + age);
    }
}
