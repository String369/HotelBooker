package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    //Map<K,V> --> Key =String(type) :"email" , Value = Customer(type) : Firstname, Lastname, email together
    private final Map<String, Customer> customers = new HashMap<>();

    /*Static Reference*/
    private CustomerService() {
        // Private constructor prevents direct instantiation from outside the class
    }
    private static class SingletonHolder {
        private static final CustomerService SINGLETON = new CustomerService();
    }
    public static CustomerService getSingleton() {
        return SingletonHolder.SINGLETON;
    }



    /*Adding customer to Collections, will utilize Map to store, retrieve and process data*/
    public void addCustomer(final String email, final String firstName, final String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    /* Here comparing customer email in customers with given customerEmail,
    if the given customerEmail is there in database, it will give the customer information associated with this email*/
    public Customer getCustomer(final String customerEmail) {
        for (Customer customer : customers.values()) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    /* Getting all the customers from HashMap , Here values will give the customer values stored in the Map*/
    public Collection<Customer> getAllCustomers() {
        Collection<Customer> allCustomers = new ArrayList<>();
        for (Customer customer : customers.values()) {
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
