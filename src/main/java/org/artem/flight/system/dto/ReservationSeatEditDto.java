package org.artem.flight.system.dto;

import lombok.Data;

@Data
public class ReservationSeatEditDto {


    Long id;
    Integer price;
    Long seatId;

    public ReservationSeatEditDto() {
    }

    public ReservationSeatEditDto(Integer price) {
        this.price = price;
    }
}