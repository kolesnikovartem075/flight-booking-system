package org.artem.flight.system.http.controller;

import lombok.Value;

@Value
public class CustomerRegistrationCreateEditDto {

    String email;
    String rawPassword;
}