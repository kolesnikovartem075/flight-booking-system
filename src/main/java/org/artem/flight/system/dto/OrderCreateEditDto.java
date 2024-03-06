package org.artem.flight.system.dto;

import lombok.Data;
import lombok.Value;
import org.artem.flight.system.database.entity.OrderStatus;
import org.w3c.dom.stylesheets.LinkStyle;

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
    List<OrderLineCreateEditDto> orderLineCreateEditDtos;
}