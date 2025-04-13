package com.tuanvn.Ecommerce.Store.service.impl;

import com.tuanvn.Ecommerce.Store.domain.PaymentOrderStatus;
import com.tuanvn.Ecommerce.Store.modal.Order;
import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import com.tuanvn.Ecommerce.Store.modal.User;
import com.tuanvn.Ecommerce.Store.repository.OrderRepository;
import com.tuanvn.Ecommerce.Store.repository.PaymentOrderRepository;
import com.tuanvn.Ecommerce.Store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        System.out.println("Creating order...");
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(1L); // Dummy ID
        paymentOrder.setUser(user);
        paymentOrder.setStatus(PaymentOrderStatus.PENDING);
        System.out.println("Order created with ID: " + paymentOrder.getId());
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderById(String orderId) {
        System.out.println("Fetching payment order by ID: " + orderId);
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(Long.parseLong(orderId)); // Dummy data
        System.out.println("Payment order found with ID: " + paymentOrder.getId());
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        System.out.println("Fetching payment order by Payment ID: " + paymentId);
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setPaymentLinkId(paymentId); // Dummy data
        System.out.println("Payment order found with Payment ID: " + paymentOrder.getPaymentLinkId());
        return paymentOrder;
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) {
        System.out.println("Processing payment order...");
        System.out.println("Payment ID: " + paymentId + ", Payment Link ID: " + paymentLinkId);
        if (paymentOrder == null || paymentId == null || paymentLinkId == null) {
            System.out.println("Payment processing failed due to null values.");
            return false;
        }
        paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
        paymentOrder.setPaymentLinkId(paymentLinkId);
        System.out.println("Payment processed successfully for Payment ID: " + paymentId);
        return true;
    }

    @Override
    public String createVnpayPaymentUrl(User user, Long amount, Long orderId) {
        System.out.println("Creating VNPay payment URL...");
        String paymentUrl = "https://dummy-vnpay-url.com?amount=" + amount + "&orderId=" + orderId;
        System.out.println("VNPay payment URL created: " + paymentUrl);
        return paymentUrl;
    }
}