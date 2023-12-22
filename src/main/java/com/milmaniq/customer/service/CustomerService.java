package com.milmaniq.customer.service;

import com.milmaniq.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomer(Long id);

    List<CustomerDto> getAllCustomers();

    CustomerDto addCustomer(CustomerDto customerDto);

    Boolean removeCustomer(Long id);
}
