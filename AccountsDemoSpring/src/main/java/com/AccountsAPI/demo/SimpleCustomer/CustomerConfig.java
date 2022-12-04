package com.AccountsAPI.demo.SimpleCustomer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args->{
            Customer cus1 = new Customer(
                    1,
                    "First Name",
                    "Last Name",
                    97685214,
                    "email@test.com",
                    "Honduras",
                    "G2351472B",
                    "Passport",
                    "Single",
                    "English",
                    "Janitor"
            );
            Customer cus2 = new Customer(
                    2,
                    "First Name2",
                    "Last Name2",
                    976852142,
                    "email2@test.com",
                    "Honduras",
                    "G2351472B2",
                    "Passport",
                    "Single",
                    "English",
                    "Janitor"
            );
            Customer cus3 = new Customer(
                    3,
                    "First Name3",
                    "Last Name3",
                    976852143,
                    "email3@test.com",
                    "Honduras",
                    "G2351472B3",
                    "Passport",
                    "Single",
                    "English",
                    "Janitor"
            );
            customerRepository.saveAll(List.of(cus1,cus2,cus3));
        };
    }
}
