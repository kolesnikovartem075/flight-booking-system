package org.artem.flight.database.entity;

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
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airport airport;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "flight")
    List<Seat> seats = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight")
    List<Schedule> schedules = new ArrayList<>();

    private String flightNo;
    private Integer seatCapacity;
}
