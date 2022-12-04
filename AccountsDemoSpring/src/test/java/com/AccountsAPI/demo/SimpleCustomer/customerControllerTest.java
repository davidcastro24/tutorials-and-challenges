package com.AccountsAPI.demo.SimpleCustomer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.AccountsAPI.demo")
@WebMvcTest(CustomerController.class)
public class customerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    private final String baseURI = "/api/v1/customer/";
    private final String getCustomersURI = baseURI+"getAll/";
    private final String getCustomerByIdURI = baseURI+"getById/";

    List<Customer> mockedCustomers = List.of(createValidCustomer(),createValidCustomerAllFields());
    Customer customer;
    @Before
    public void clearAll(){
        customerRepository.deleteAll();
    }

    @Before
    public void setVariables(){
         customer = createValidCustomerAllFields();
    }

    @Test
    public void getAllCustomers_IfCustomersReturnList() throws Exception{
        Mockito.when(customerService.getAllCustomers()).thenReturn(mockedCustomers);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getCustomersURI).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getCustomerById_IfCustomersReturnCustomer() throws Exception{
        Mockito.when(customerService.getCustomerById(customer.getCustomerId())).thenReturn(createValidCustomer());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getCustomerByIdURI+customer.getCustomerId())
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void getCustomerByCode_IfCustomersReturnCustomer() throws Exception{
        Mockito.when(customerService.getCustomerByCustomerCode(customer.getCustomerCode())).thenReturn(customer);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(baseURI+customer.getCustomerCode())
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void addCustomerWithAllFields_ReturnOk() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(baseURI)
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    @Test
    public void addCustomerWithRequiredFields_ReturnOk() throws Exception {
        Customer customer = createValidCustomer();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(baseURI)
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void putCustomer_ReturnOk() throws Exception {
        customer.setPhoneNumber(12345678);
        customer.setLanguage("Spanish");
        customer.setJob("Programmer");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(baseURI+"/"+customer.getCustomerCode())
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void deleteCustomer_ReturnOk() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(baseURI+"/"+customer.getCustomerCode())
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString() + "----" + result.getResponse().getStatus());
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    public String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

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


}
