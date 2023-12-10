package org.artem.flight.booking.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Flight implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Airline airline;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    List<Seat> seats = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    List<Schedule> schedules = new ArrayList<>();

    private String flightNo;
    private Integer seatCapacity;
}
