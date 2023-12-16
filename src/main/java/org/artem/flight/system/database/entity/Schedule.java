package org.artem.flight.system.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "start_airport_id")
    private Airport start;

    @ManyToOne
    @JoinColumn(name = "end_airport_id")
    private Airport destination;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String status;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<ReservationSeat> reservationSeats;
}
