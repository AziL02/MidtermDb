package com.example.MidtermDb.Product;

import com.example.MidtermDb.Customers.CustomersRepository;
import com.example.MidtermDb.Exeption.ResourceNotFoundException;
import com.example.MidtermDb.Exeption.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping("/customers/all/products")
    public List<Products> getProducts() {
        return productsRepository.findAll();
    }
    @GetMapping("/customers/{id}/products")
    public List<Products> getAllCommentsByPostId(@PathVariable (value = "id") Long id) {
        return productsRepository.findByCustomersId(id);
    }

    @PostMapping("/customers/{customersId}/products")
    public Products createProducts(@PathVariable (value = "customersId") Long customersId,
                                    @Valid @RequestBody Products products) throws ResourceNotFoundException {
        return customersRepository.findById(customersId).map(customers -> {
            products.setCustomers(customers);
            return productsRepository.save(products);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer Id " + customersId + " not found"));
    }

    @PutMapping("/customers/{customersId}/products/{productsId}")
    public Products updateProducts(@PathVariable (value = "customersId") Long customersId,
                                   @PathVariable (value = "productsId") Long productsId,
                                   @Valid @RequestBody Products productsDetails) throws ResourceNotFoundException {
        if(!customersRepository.existsById(customersId)) {
            throw new ResourceNotFoundException("Product Id " + customersId + " not found");
        }
        return productsRepository.findById(productsId).map(products -> {
            products.setProducts_name(productsDetails.getProducts_name());
            products.setQuantity(productsDetails.getQuantity());
            products.setPrice(productsDetails.getPrice());
            return productsRepository.save(products);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer Id " + productsId + "not found"));
    }


    @DeleteMapping(value = "/customers/{customerId}/products/{product}")
    public Status deleteProducts(@PathVariable("productsId") Long productsId, @PathVariable String customersId) {
        boolean exists = productsRepository.existsById(productsId);
        if (!exists) {
            throw new IllegalStateException("Products with Id " + productsId + " does not exists");
        }
        productsRepository.deleteById(productsId);
        return Status.Deleted;
    }

    @DeleteMapping("/customers/all/products/all")
    public Status deleteAllProducts() {
        customersRepository.deleteAll();
        return Status.Deleted;
    }
}