package org.artem.flight.system.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport implements BaseEntity<Long> {

    @Id
    private Long id;

    @ManyToOne
    private Address address;

    private String name;
}
