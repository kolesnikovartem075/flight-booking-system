package org.artem.flight.system.database.entity;

import javax.persistence.*;

import lombok.*;

@EqualsAndHashCode(exclude = {"flight"})
@ToString(exclude = {"flight"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    private String numberNo;

    @Enumerated(EnumType.STRING)
    private SeatRank rank;
}