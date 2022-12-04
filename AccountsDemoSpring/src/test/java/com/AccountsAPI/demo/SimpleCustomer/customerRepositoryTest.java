package com.AccountsAPI.demo.SimpleCustomer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.AccountsAPI.demo")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class customerRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CustomerRepository customerRepository;

    public Customer createValidCustomer(){
        return new Customer(
                "First Name",
                "Last Name",
                976852134,
                "emailtest1@test.com",
                "Honduras",
                "G2351472B23",
                "Passport"
        );
    }

    public Customer createValidCustomerAllFields(){
        return new Customer(
                7,
                "First Name",
                "Last Name",
                97521454,
                "emailtest@test.com",
                "Honduras",
                "G2351472B52",
                "Passport",
                "Single",
                "English",
                "Janitor"
        );
    }

    @Test
    public void findCustomerById_IfExistsReturnCustomer(){
        Customer customerInDB = createValidCustomer();
        testEntityManager.persist(customerInDB);
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(customerInDB.getCustomerId());
        assertThat(foundCustomer).isNotNull();
    }

    @Test
    public void findCustomerById_IfNotExistsReturnNull(){
        Customer foundCustomer = customerRepository.findCustomerByCustomerId("testnullid");
        assertThat(foundCustomer).isNull();
    }


}
