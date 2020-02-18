package ar.com.javacuriosities.rs.repository;

import ar.com.javacuriosities.rs.exceptions.CustomerNotFoundException;
import ar.com.javacuriosities.rs.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private static final Map<Integer, Customer> customers = new HashMap<>();

    private static Integer lastID;

    static {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Cosme");
        customer1.setAge(96);

        customers.put(customer1.getId(), customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Pedro");
        customer2.setAge(97);

        customers.put(customer2.getId(), customer2);

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("Pablo");
        customer3.setAge(98);

        customers.put(customer3.getId(), customer3);

        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setName("Sancho");
        customer4.setAge(99);

        customers.put(customer4.getId(), customer4);

        lastID = customers.size();
    }

    public static Customer getCustomer(Integer id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(String.format("Customer %d not found", id));
        }
        return customer;
    }

    public static List<Customer> getCustomers() {
        return new ArrayList<>(customers.values());
    }

    public static List<Customer> getCustomerByName(String name) {
        List<Customer> customers = new ArrayList<>();
        for (Map.Entry<Integer, Customer> entry : CustomerRepository.customers.entrySet()) {
            Customer currentCustomer = entry.getValue();
            String clientName = currentCustomer.getName();
            if (clientName != null && clientName.contains(name)) {
                customers.add(currentCustomer);
            }
        }
        return customers;
    }

    public static Customer saveCustomer(Customer customer) {
        lastID += 1;
        customer.setId(lastID);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public static Customer updateCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    public static void deleteCustomer(int id) {
        customers.remove(id);
    }
}
