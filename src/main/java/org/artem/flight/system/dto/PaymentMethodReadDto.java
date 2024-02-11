package org.artem.flight.system.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class PaymentMethodReadDto {

    Integer accountNumber;
    LocalDate expiryDate;
}