package com.tuanvn.Ecommerce.Store.service;

import com.tuanvn.Ecommerce.Store.modal.Order;
import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import com.tuanvn.Ecommerce.Store.modal.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public interface PaymentService {
    String createVNPAYPaymentUrl(Long orderId, HttpServletRequest request) throws Exception;
    PaymentOrder processVNPAYReturn(HttpServletRequest request);
    PaymentOrder createPaymentOrder(Long amount, String orderInfo, User user, Set<Order> orders); // Tạo đơn hàng
}
