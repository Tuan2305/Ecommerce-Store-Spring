package com.tuanvn.Ecommerce.Store.modal;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity // anh xa 1 bang trong database
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SellerReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings = 0L; // thong thu nhap

    private Long totalSales = 0L; // tong gia ban

    private Long totalRefunds = 0L; // tong tien hoan tra

    private Long totalTax = 0L; // tong tien thue

    private Long netEarnings = 0L; // tong thu nhap thuc

    private Integer totalOrders = 0; // tong don hang

    private Integer canceledOrders = 0; // thong don tra ve

    private Integer totalTransactions = 0; // so don giao dich
}

