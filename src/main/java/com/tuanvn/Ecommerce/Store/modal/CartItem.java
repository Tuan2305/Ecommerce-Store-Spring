package com.tuanvn.Ecommerce.Store.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.security.PrivateKey;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @JsonIgnore
    @ManyToMany
    private Cart cart;

    private Product product;

    private String size;

    private int quantity = 1;

    private Integer Price;

    private Integer sellingPrice;

    private Long userId;

}
