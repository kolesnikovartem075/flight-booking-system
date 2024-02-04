package org.artem.flight.system.database.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "reservation_seat")
public class ReservationSeat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Seat seat;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    private Schedule schedule;
}
