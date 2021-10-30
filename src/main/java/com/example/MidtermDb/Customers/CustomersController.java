package com.example.MidtermDb.Customers;


import com.example.MidtermDb.Exeption.ResourceNotFoundException;
import com.example.MidtermDb.Exeption.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomersController {
    @Autowired
    private CustomersRepository customersRepository;

    //Get Customers
    @GetMapping("/customers")
    public List<Customers> getAllEmp() {
        return this.customersRepository.findAll();
    }

    //Get Customers by Id
    @GetMapping("/customers/{id}")
    public Optional<Customers> getCustomer(@PathVariable("id") Long id) {
        return customersRepository.findById(id);
    }

    //Save Customers
    @PostMapping("/savecustomers")
    public Status createCustomers(@Valid @RequestBody Customers nCustomers) {
        List<Customers> customers = customersRepository.findAll();

        System.out.println("New customer: " + nCustomers.toString());

        for (Customers customerq : customers) {
            System.out.println("Customer Added: " + nCustomers.toString());

            if (customerq.equals(nCustomers)) {
                System.out.println("Customer Already exists!");
                return Status.Customer_Already_Exist;
            }
        }
        customersRepository.save(nCustomers);
        return Status.Customer_Registered;
    }
//     Login Customers
    @PostMapping("/customers/login")
    public Status loginCustomers(@Valid @RequestBody Customers customers) {
        List<Customers> customer = customersRepository.findAll();
        for (Customers other : customer) {
            if (other.equals(customers)) {
                customersRepository.save(customers);
                return Status.Login_Successful;
            }
        }        return Status.Invalid_Attempt;
    }

    @PostMapping("/customers/logout")
    public Status logOutCustomers(@Valid @RequestBody Customers customers) {
        List<Customers> customer = customersRepository.findAll();
        for (Customers other : customer) {
            if (other.equals(customers)) {
                customersRepository.save(customers);
                return Status.Logout_Successful;
            }
        }        return Status.Invalid_Attempt;
    }

    //Update Customers
    @PutMapping("/updatecustomers/{id}")
    public ResponseEntity<Customers> updateCustomers(@PathVariable(value = "id") Long customersId,
                                                     @Valid @RequestBody Customers customersDetail)
            throws ResourceNotFoundException {
        Customers customers = customersRepository.findById(customersId)
                .orElseThrow(() -> new ResourceNotFoundException("Customers id Not Found" + customersId));

        customers.setEmail(customersDetail.getEmail());
        customers.setName(customersDetail.getName());
        customers.setAge(customersDetail.getPassword());
        return ResponseEntity.ok(this.customersRepository.save(customers));

    }
    //  Delete Customers by Id
    @DeleteMapping("/deletecustomers/{id}")
    public Map<String, Boolean> deleteCustomers(@PathVariable(value = "id") Long customersId) throws ResourceNotFoundException {
        Customers customers = customersRepository.findById(customersId)
                .orElseThrow(() -> new ResourceNotFoundException("Customers id Not Found :" + customersId));

        this.customersRepository.delete(customers);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
    // Delete All Customers
    @DeleteMapping("/deleteAll")
    public Status deleteCustomers() {
        customersRepository.deleteAll();
        return Status.Deleted;
    }
}
