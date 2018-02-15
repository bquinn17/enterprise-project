package com.erp.sales.controller;

import com.erp.sales.model.Customer;
import com.erp.sales.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by bquinn on 2/14/18.
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // Get All Customers
    @GetMapping("/customers")
    public List<Customer> getAllNotes() {
        return customerRepository.findAll();
    }

    // Create a new Customer
    @PostMapping("/customers")
    public Customer createNote(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Get a Single Customer
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getNoteById(@PathVariable(value = "id") Long cusomterId) {
        Customer customer = customerRepository.findOne(cusomterId);
        if(customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer);
    }

    // Update a Customer
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findOne(noteId);
        if(customer == null) {
            return ResponseEntity.notFound().build();
        }
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());

        Customer updatedNote = customerRepository.save(customer);
        return ResponseEntity.ok(updatedNote);
    }

    // Delete a Customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteNote(@PathVariable(value = "id") Long noteId) {
        Customer note = customerRepository.findOne(noteId);
        if(note == null) {
            return ResponseEntity.notFound().build();
        }

        customerRepository.delete(note);
        return ResponseEntity.ok().build();
    }
}

