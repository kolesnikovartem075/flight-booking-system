package org.artem.flight.system.dto;

import lombok.Data;
import lombok.Value;
import org.artem.flight.system.database.entity.OrderStatus;

@Data
public class OrderCreateEditDto {

    String email;
    String sessionId;
    Long paymentMethodId;
    Long shoppingCartId;
    Integer total;
    OrderStatus orderStatus;
    PaymentMethodReadDto paymentMethod;


}