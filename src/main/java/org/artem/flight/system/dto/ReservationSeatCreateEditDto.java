package org.artem.flight.system.dto;

import lombok.Data;
import lombok.Value;

@Data
public class ReservationSeatCreateEditDto {


    Long id;
    Long scheduleId;
    Long seatId;
    Long customerId;
    Integer price;
    String status;

    public ReservationSeatCreateEditDto() {
    }

    public ReservationSeatCreateEditDto(Long scheduleId, Long seatId, Long customerId, Integer price, String status) {
        this.scheduleId = scheduleId;
        this.seatId = seatId;
        this.customerId = customerId;
        this.price = price;
        this.status = status;
    }

    public ReservationSeatCreateEditDto(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}