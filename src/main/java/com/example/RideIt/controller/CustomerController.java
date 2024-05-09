package com.example.RideIt.controller;

import com.example.RideIt.Enum.Gender;
import com.example.RideIt.dto.request.CustomerRequest;
import com.example.RideIt.dto.response.CustomerResponse;
import com.example.RideIt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
//    @Autowired
//    CustomerService customerService; fiel injection

    private final CustomerService customerService;
    //constructor injection
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse response = customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //update details


    //get all Customers of a particular gender and above particular age
    @GetMapping("/gender/{gender}/age/{age}")
    public ResponseEntity<List<CustomerResponse>> getCustomerByGenderAndAgeGreaterThan(@PathVariable("gender") Gender gender,
                                                                                       @PathVariable("age") int age){
        List<CustomerResponse> customerResponses = customerService.getCustomerByGenderAndAgeGreaterThan(gender,age);
        return new ResponseEntity<>(customerResponses,HttpStatus.FOUND);
    }

    //update the existing customer

    @PutMapping()
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse response = customerService.updateCustomer(customerRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteCustomerByEmail(@RequestParam int id){
        customerService.deletedCustomer(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
