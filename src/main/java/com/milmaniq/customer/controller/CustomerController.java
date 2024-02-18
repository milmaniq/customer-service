package com.milmaniq.customer.controller;

import com.milmaniq.customer.dto.CustomerDto;
import com.milmaniq.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Value("${app.custom-variable}")
    private String customVariable;

    @GetMapping("/variable-value")
    public ResponseEntity<String> getValueOfCustomVariable() {
        final String value = "The value of the custom variable is: " + this.customVariable;
        return ResponseEntity.ok(value);
    }

    @GetMapping()
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping()
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @DeleteMapping("/{customerId}")
    public Boolean removeCustomer(@PathVariable Long customerId) {
        return customerService.removeCustomer(customerId);
    }
}
