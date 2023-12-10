package org.artem.flight.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport implements BaseEntity<Integer> {

    @Id
    private Integer id;

    @OneToMany
    private List<Flight> flights;

    @ManyToOne
    private Address address;

    private String name;
}
