package com.milmaniq.customer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milmaniq.customer.dto.CustomerDto;
import com.milmaniq.customer.entity.Customer;
import com.milmaniq.customer.repository.CustomerRepository;
import com.milmaniq.customer.service.CustomerService;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomer(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer Not Found");
        }

        Customer customer = customerOptional.get();

        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerListOptional = customerRepository.findAll();

        return customerListOptional.stream()
                .filter(Objects::nonNull)
                .map(entity -> new CustomerDto(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = jsonSchemaFactory
                .getSchema(JsonSchema.class.getResourceAsStream("/json-validators/customer-json-validator.json"));

        JsonNode customerJson = new ObjectMapper().convertValue(customerDto, JsonNode.class);
        Set<ValidationMessage> errors = jsonSchema.validate(customerJson);
        // the errors would be something like this - [  $.id: null found, integer expected,
        //                                              $.name: null found, string expected]

        if (!errors.isEmpty()) {
            return new CustomerDto(null, errors.toString());
        }

        Customer customer = customerRepository.save(Customer.builder().name(customerDto.getName()).build());

        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    @Override
    public Boolean removeCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
