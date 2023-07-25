package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @GetMapping("/getalladata")
    public ResponseEntity<List<Customer>> getAllData() {

        return ResponseEntity.ok(customerServiceImpl.getAllData());
    }

    @PostMapping("/savealldata")

    public ResponseEntity<Customer> saveAllData(@RequestBody Customer customer) {

        return ResponseEntity.ok(customerServiceImpl.saveData(customer));
    }

    @GetMapping("/getDataByName")
    public ResponseEntity<List<Customer>> getDataByName(@PathVariable String custName) {

        return ResponseEntity.ok(customerServiceImpl.getDataByName(custName));
    }

    @GetMapping("/getDataById/{custId}")
    public ResponseEntity<Optional<Customer>> getDataById(@PathVariable int custId) {

        return ResponseEntity.ok(customerServiceImpl.getDataById(custId));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName() {

        return ResponseEntity.ok(customerServiceImpl.sortDataByName());
    }

    @GetMapping("/sortbyaccbalance")
    public ResponseEntity<List<Customer>> sortByAccBalnce() {

        return ResponseEntity.ok(customerServiceImpl.sortByAccBalance());
    }

    @GetMapping("/sortbycustid")
    public ResponseEntity<List<Customer>> sortByCustId() {

        return ResponseEntity.ok(customerServiceImpl.sortByCustId());
    }

    @PutMapping("/updatedata/{custId}")
    public ResponseEntity<Customer> updateData(@PathVariable int custId, @RequestBody Customer customer) {
        Customer customer1 = customerServiceImpl.getDataById(custId).orElseThrow(() -> new RecordNotFoundException("cust Id Does not Exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAccBalance(customer.getCustAccBalance());
        customer1.setCustDOB(customer.getCustDOB());

        return ResponseEntity.ok(customerServiceImpl.saveData(customer1));
    }

    @DeleteMapping("/deletedatabyid/{custId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int custId) {
        customerServiceImpl.deleteDataById(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {

        customerServiceImpl.deleteAllData();
        return ResponseEntity.ok("Delete All Data");

    }


}
