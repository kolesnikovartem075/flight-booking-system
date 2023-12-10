package org.artem.flight.booking.system.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class FlightBookingApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(FlightBookingApplication.class, args);

        System.out.println(context.getBeanDefinitionCount());
    }
}