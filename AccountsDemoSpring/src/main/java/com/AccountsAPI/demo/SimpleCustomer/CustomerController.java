package com.AccountsAPI.demo.SimpleCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(path = "/getAll")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/getById/{CustomerID}")
    public Customer getCustomerById(@PathVariable("CustomerID") String customerID){
        return customerService.getCustomerById(customerID);
    }

    @GetMapping(path = "/{CustomerCode}")
    public Customer getCustomerByCode(@PathVariable("CustomerCode") Integer customerCode){
        return customerService.getCustomerByCustomerCode(customerCode);
    }

    @PostMapping()
    public void postCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @PutMapping("/{customerCode}")
    public void putCustomer(@PathVariable Integer customerCode, @RequestBody Customer customer){
        customerService.putCustomer(customerCode,customer);
    }

    @DeleteMapping("/{customerCode}")
    public void deleteCustomer(@PathVariable Integer customerCode){
        customerService.deleteCustomer(customerCode);
    }

}
