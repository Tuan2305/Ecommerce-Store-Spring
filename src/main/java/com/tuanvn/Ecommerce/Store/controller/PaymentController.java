package com.tuanvn.Ecommerce.Store.controller;

import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import com.tuanvn.Ecommerce.Store.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final OrderService orderService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;


    public PaymentController(PaymentService paymentService, UserService userService, SellerService sellerService, OrderService orderService, SellerReportService sellerReportService, TransactionService transactionService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.sellerService = sellerService;
        this.orderService = orderService;
        this.sellerReportService = sellerReportService;
        this.transactionService = transactionService;
    }

    /**
     * Endpoint to create a VNPAY payment URL.
     * @param orderId The ID of the order to be paid.
     * @param request The HTTP request object.
     * @return The VNPAY payment URL.
     * @throws Exception If an error occurs while generating the payment URL.
     */
    @GetMapping("/vnpay")
    public ResponseEntity<String> createVNPAYPayment(@RequestParam Long orderId, HttpServletRequest request) throws Exception {
        String paymentUrl = paymentService.createVNPAYPaymentUrl(orderId, request);
        return ResponseEntity.ok(paymentUrl);
    }

    /**
     * Endpoint to process the return from VNPAY after payment.
     * @param request The HTTP request object containing VNPAY return parameters.
     * @return The payment order with the status of the transaction.
     */
    @GetMapping("/vnpay-return")
    public ResponseEntity<PaymentOrder> processVNPAYReturn(HttpServletRequest request) {
        PaymentOrder paymentOrder = paymentService.processVNPAYReturn(request);
        return ResponseEntity.ok(paymentOrder);
    }
}