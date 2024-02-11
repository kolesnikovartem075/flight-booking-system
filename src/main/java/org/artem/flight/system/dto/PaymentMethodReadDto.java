package org.artem.flight.system.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PaymentMethodReadDto {

    Long id;

    Integer accountNumber;
    LocalDate expiryDate;
}