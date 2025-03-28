package com.tuanvn.Ecommerce.Store.modal;

import jakarta.persistence.*;
import lombok.*;

import java.lang.ref.PhantomReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer discount;

    @OneToOne
    private HomeCategory category;

}
