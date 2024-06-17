package org.artem.flight.system.http.controller;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class CustomerRegistrationCreateEditDto {

    @Email
    @NotBlank
    String email;

    @NotBlank
    String rawPassword;
}