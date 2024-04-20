package com.example.RideIt.repository;

import com.example.RideIt.Enum.Gender;
import com.example.RideIt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

//    @Query(value = "select * from customer where gender = :g and age>= :age",nativeQuery = true)
    //This is normal mysql query thus native query=true
    @Query(value = " Select c from Customer c where c.gender=:g and c.age=:age")//this is in form of java query
    List<Customer> getByGenderAndAgeGreaterThan(Gender g, int age);
}
