package org.artem.flight.system.database.entity;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@ToString(exclude = {"reservationSeats", "flight"})
@EqualsAndHashCode(exclude = {"reservationSeats", "flight"})
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

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "schedule")
    private List<ReservationSeat> reservationSeats;
}
