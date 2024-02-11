package org.artem.flight.system.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "items")
@EqualsAndHashCode(exclude = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShoppingCart implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;

    @OneToMany(mappedBy = "shoppingCart")
    private List<ShoppingCartItem> items;
}
