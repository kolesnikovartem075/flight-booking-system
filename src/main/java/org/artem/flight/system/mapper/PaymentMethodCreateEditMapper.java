package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.database.entity.PaymentMethod;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.dto.PaymentMethodCreateEditDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMethodCreateEditMapper implements Mapper<PaymentMethodCreateEditDto, PaymentMethod> {

    private final CustomerRepository customerRepository;

    @Override
    public PaymentMethod map(PaymentMethodCreateEditDto object) {
        PaymentMethod paymentMethod = new PaymentMethod();
        copy(object, paymentMethod);

        return paymentMethod;
    }

    @Override
    public PaymentMethod map(PaymentMethodCreateEditDto fromObject, PaymentMethod toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(PaymentMethodCreateEditDto object, PaymentMethod paymentMethod) {
        Customer customer = customerRepository.findById(object.getCustomerId())
                .orElseThrow();

        paymentMethod.setCustomer(customer);
        paymentMethod.setExpiryDate(object.getExpiryDate());
        paymentMethod.setAccountNumber(object.getAccountNumber());
    }
}