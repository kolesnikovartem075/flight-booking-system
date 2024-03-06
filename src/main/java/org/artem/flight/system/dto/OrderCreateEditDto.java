package org.artem.flight.system.dto;

import lombok.Data;
import org.artem.flight.system.database.entity.OrderStatus;

import java.util.List;

@Data
public class OrderCreateEditDto {

    String email;
    String sessionId;
    Long paymentMethodId;
    Long shoppingCartId;
    Integer total;
    OrderStatus orderStatus;
    PaymentMethodReadDto paymentMethod;
    List<OrderLineCreateEditDto> orderLines;
}