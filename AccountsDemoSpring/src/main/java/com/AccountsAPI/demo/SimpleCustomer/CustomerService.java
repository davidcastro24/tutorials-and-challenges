package com.AccountsAPI.demo.SimpleCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer){
        Customer customerFoundById = customerRepository.findCustomerByCustomerId(customer.getCustomerId());
        if (customerFoundById != null){
            throw new IllegalStateException("Customer already exists");
        }else{
            customerRepository.save(customer);
        }
    }

    public void deleteCustomer(Integer CustomerCode){
        Optional<Customer> customerInDB = Optional.of(customerRepository.getReferenceById(CustomerCode));
        if (!customerInDB.isPresent()){
            throw new IllegalStateException("Customer with code " + CustomerCode + " does not exist");
        }else{
            customerRepository.delete(customerInDB.get());
        }

    }

    public void putCustomer(Integer CustomerCode, Customer customer){
        Optional<Customer> customerInDB = customerRepository.findById(CustomerCode);
        if (!customerInDB.isPresent()) {
            throw new IllegalStateException("Customer with code " + CustomerCode + " does not exist");
        }else{
            Customer updatedCustomer = customerInDB.get();
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setLanguage(customer.getLanguage());
            updatedCustomer.setJob(customer.getJob());
            updatedCustomer.setMaritalStatus(customer.getMaritalStatus());
            updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(updatedCustomer);

        }
    }

    public Customer getCustomerByCustomerCode(Integer CustomerCode){
        Optional<Customer> customer = customerRepository.findById(CustomerCode);
        if (customer.isPresent()){
            return customer.get();
        }else{
            throw new IllegalStateException("Customer with code " + CustomerCode + " does not exist");
        }
    }

    public Customer getCustomerById(String CustomerID){
        Optional<Customer> customer = Optional.of(customerRepository.findCustomerByCustomerId(CustomerID));
        if (customer.isPresent()){
            return customer.get();
        }else{
            throw new IllegalStateException("Customer with id " + CustomerID + " does not exist");
        }
    }





}
