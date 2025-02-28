package com.tuanvn.Ecommerce.Store.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne

    private Order order;

    @ManyToOne
    private Product product;

    private String size;

    private int quantity;

    private Integer price; // mrpPrice = price

    private Integer sellingPrice;

    private Long userId;

}
