package org.artem.flight.system.dto;

import lombok.Value;
import org.artem.flight.system.database.entity.OrderStatus;

@Value
public class OrderCreateEditDto {

    String email;
    String sessionId;
    Long paymentMethodId;
    Long shoppingCartId;
    Integer total;
    OrderStatus orderStatus;
}