package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.dto.CustomerCreateEditDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCreateEditMapper implements Mapper<CustomerCreateEditDto, Customer> {

    @Override
    public Customer map(CustomerCreateEditDto object) {
        Customer customer = new Customer();
        copy(object, customer);

        return customer;
    }

    @Override
    public Customer map(CustomerCreateEditDto fromObject, Customer toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(CustomerCreateEditDto object, Customer customer) {
        customer.setEmail(object.getEmail());
    }
}