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
public class Airport implements BaseEntity<Long> {

    @Id
    private Long id;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airport")
    private List<Flight> flights = new ArrayList<>();

    @ManyToOne
    private Address address;

    private String name;
}
