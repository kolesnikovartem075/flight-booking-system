package org.artem.flight.system.mapper;

import lombok.RequiredArgsConstructor;
import org.artem.flight.system.database.entity.Customer;
import org.artem.flight.system.database.entity.Order;
import org.artem.flight.system.database.entity.OrderStatus;
import org.artem.flight.system.database.entity.PaymentMethod;
import org.artem.flight.system.database.repository.CustomerRepository;
import org.artem.flight.system.database.repository.PaymentMethodRepository;
import org.artem.flight.system.dto.OrderCreateEditDto;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order> {

    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public Order map(OrderCreateEditDto orderCreateEditDto) {
        Order order = new Order();
        copy(orderCreateEditDto, order);


        return order;
    }

    public Order map(OrderCreateEditDto orderCreateEditDto, @MappingTarget Order entity) {
        entity.setOrderStatus(orderCreateEditDto.getOrderStatus());
        return entity;
    }


    private void copy(OrderCreateEditDto orderCreateEditDto, Order order) {
        var customer = getCustomer(orderCreateEditDto);
        var paymentMethod = getPaymentMethod(orderCreateEditDto);

        order.setCustomer(customer);
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setDateCreated(LocalDate.now());
        order.setOrderTotal(orderCreateEditDto.getTotal());
    }

    private Customer getCustomer(OrderCreateEditDto orderCreateEditDto) {
        return customerRepository.findById(orderCreateEditDto.getCustomerId())
                .orElseThrow();
    }

    private PaymentMethod getPaymentMethod(OrderCreateEditDto orderCreateEditDto) {
        return paymentMethodRepository.findById(orderCreateEditDto.getPaymentMethodId())
                .orElseThrow();
    }
}