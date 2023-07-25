package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.model.Product;
import com.csi.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/getallproductdata")
    public ResponseEntity<List<Product>> getAllData() {

        return ResponseEntity.ok(productServiceImpl.getAllData());
    }

    @PostMapping("/savedata")
    public ResponseEntity<Product> saveAllData(@RequestBody Product product) {

        return ResponseEntity.ok(productServiceImpl.saveData(product));
    }
    @GetMapping("/getproductbyprice")
    public ResponseEntity<?> getProductByPrice(double prductPrice) {

      List<Product>  product = productServiceImpl.getProductByPrice(prductPrice);

      return ResponseEntity.ok(productServiceImpl.getProductByPrice(prductPrice));

    }
    @GetMapping("/sortbyid")
    public ResponseEntity<List<Product>> sortById() {

        return ResponseEntity.ok(productServiceImpl.sortDataById());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Product>> sortByName() {

        return ResponseEntity.ok(productServiceImpl.sortByName());
    }

    @GetMapping("/sortbyprice")
    public ResponseEntity<List<Product>> sortByPrice() {

        return ResponseEntity.ok(productServiceImpl.sortbyprice());
    }

    @PutMapping("/updatedatabyid/{productId}")
    public ResponseEntity<Product> updateDataByProductId(@PathVariable int productId, @RequestBody Product product) {
        Product product1 = productServiceImpl.getDataById(productId).orElseThrow(() -> new RecordNotFoundException("Product Id Does Not Exist"));

        product1.setProductPrice(product.getProductPrice());
        product1.setProductName(product.getProductName());
        product1.setProducyLaunchDate(product.getProducyLaunchDate());

        return ResponseEntity.ok(productServiceImpl.updateProductData(product1));
    }

    @DeleteMapping("/deletedatabyid/{productId}")
    public ResponseEntity<String> deleteDataById(int productId) {
        productServiceImpl.deleteDataById(productId);
        return ResponseEntity.ok("All Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {

        productServiceImpl.deleteAllData();
        return ResponseEntity.ok("All DataDeleted Successfully");
    }



}
