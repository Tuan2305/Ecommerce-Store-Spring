package com.tuanvn.Ecommerce.Store.repository;

import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

    // Find PaymentOrder by transaction ID
    PaymentOrder findByTransactionId(String transactionId);

    // Find all PaymentOrders by user ID
    @Query("SELECT p FROM PaymentOrder p WHERE p.user.id = :userId")
    List<PaymentOrder> findAllByUserId(@Param("userId") Long userId);

    // Find all PaymentOrders with a specific status
    List<PaymentOrder> findByStatus(String status);
}