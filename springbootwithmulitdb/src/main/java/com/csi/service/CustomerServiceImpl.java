package com.csi.service;


import com.csi.model.Customer;
import com.csi.repo.customer.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl {

    @Autowired
    CustomerRepo customerRepoImpl;

    public Customer saveData(Customer customer){

        return customerRepoImpl.save(customer);
    }
    public List<Customer> getAllData(){

        return customerRepoImpl.findAll();
    }
    public List<Customer> getDataByName(String custName){

        return getAllData().stream().filter(emp->emp.getCustName().equals(custName)).collect(Collectors.toList());
    }
    public Optional<Customer> getDataById(int custId){

        return customerRepoImpl.findById(custId);
    }
    public List<Customer> sortDataByName(){

        return getAllData().stream().sorted(Comparator.comparing(Customer::getCustName)).collect(Collectors.toList());
    }

    public List<Customer> sortByAccBalance(){

        return getAllData().stream().sorted(Comparator.comparingDouble(Customer::getCustAccBalance)).collect(Collectors.toList());
    }
    public  List<Customer> sortByCustId(){

        return getAllData().stream().sorted(Comparator.comparingInt(Customer::getCustId)).collect(Collectors.toList());
    }

    public Customer updateData(Customer customer){

        return customerRepoImpl.save(customer);
    }
    public void deleteDataById(int custId){

        customerRepoImpl.deleteById(custId);
    }
    public void deleteAllData(){

        customerRepoImpl.deleteAll();
    }
}
