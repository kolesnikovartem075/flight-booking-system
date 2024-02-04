package org.artem.flight.system.database.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@ToString(exclude = {"flights"})
@EqualsAndHashCode(exclude = {"flights"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airline implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "airline")
    List<Flight> flights;
}

