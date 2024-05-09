package com.example.RideIt.service;

import com.example.RideIt.Enum.Gender;
import com.example.RideIt.dto.request.CustomerRequest;
import com.example.RideIt.dto.response.CustomerResponse;
import com.example.RideIt.exception.CustomerNotFoundException;
import com.example.RideIt.model.Customer;
import com.example.RideIt.repository.CustomerRepository;
import com.example.RideIt.transformer.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor  // It will create all constructor with all possible combination of fields
public class CustomerService {

        @Autowired
        CustomerRepository customerRepository;


//    public CustomerService(CustomerRepository customerRepository) {
//    No need to write constructor if you have added the @RequiredConstructor;
//        this.customerRepository = customerRepository;
//    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //dto->Entity
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        //save the customer
        Customer saveCustomer = customerRepository.save(customer);

        //entity -> response dto
        return CustomerTransformer.customerToCustomerResponse(saveCustomer);
    }

    public List<CustomerResponse> getCustomerByGenderAndAgeGreaterThan(Gender gender, int age) {

        List<Customer> customers = customerRepository.getByGenderAndAgeGreaterThan(gender,age);

        List<CustomerResponse>customerResponses = new ArrayList<>();
        for(Customer customer:customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        System.out.println("Checking customer is valid or not");
        Customer customer = customerRepository.findByEmailId(customerRequest.getEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid email Id");
        }
        System.out.println("Customer valid and got it from the database");
        customer.setGender(customerRequest.getGender());
        customer.setAge(customerRequest.getAge());
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());


        Customer saved = customerRepository.save(customer);

        //entity -> response

        return CustomerTransformer.customerToCustomerResponse(saved);
    }

    public void deletedCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
