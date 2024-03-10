package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.database.entity.Role;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.http.controller.CustomerRegistrationCreateEditDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerCreateEditMapper implements Mapper<CustomerRegistrationCreateEditDto, Customer> {


    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer map(CustomerRegistrationCreateEditDto object) {
        var maybeCustomer = customerRepository.findByEmail(object.getEmail());
        if (maybeCustomer.isPresent() && maybeCustomer.get().getPassword() == null) {
            return map(object, maybeCustomer.get());
        }

        Customer customer = new Customer();
        copy(object, customer);

        return customer;
    }

    @Override
    public Customer map(CustomerRegistrationCreateEditDto fromObject, Customer toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CustomerRegistrationCreateEditDto object, Customer customer) {
        customer.setEmail(object.getEmail());
        customer.setRole(Role.CUSTOMER);

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(customer::setPassword);
    }
}