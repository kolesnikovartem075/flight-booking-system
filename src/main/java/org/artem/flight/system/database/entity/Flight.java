package org.artem.flight.system.database.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@ToString(exclude = {"seats"})
@EqualsAndHashCode(exclude = {"seats"})
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

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "flight")
    List<Seat> seats = new ArrayList<>();

    private String flightNo;
    private Integer seatCapacity;
}
