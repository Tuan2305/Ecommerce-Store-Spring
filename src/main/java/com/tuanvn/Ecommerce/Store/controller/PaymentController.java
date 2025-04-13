package com.tuanvn.Ecommerce.Store.controller;

import com.tuanvn.Ecommerce.Store.domain.PaymentOrderStatus;
import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import com.tuanvn.Ecommerce.Store.response.ApiResponse;
import com.tuanvn.Ecommerce.Store.service.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String paymentUrl = "https://dummy-vnpay-url.com?amount=100000&orderId=" + orderId;
        System.out.println("Generated VNPay payment URL: " + paymentUrl);
        return ResponseEntity.ok(paymentUrl);
    }

    /**
     * Endpoint to process the return from VNPAY after payment.
     * @param request The HTTP request object containing VNPAY return parameters.
     * @return The payment order with the status of the transaction.
     */
    @GetMapping("/vnpay-return")
    public ResponseEntity<PaymentOrder> processVNPAYReturn(HttpServletRequest request) {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId(1L); // Dummy ID
        paymentOrder.setStatus(PaymentOrderStatus.SUCCESS); // Sử dụng enum thay vì chuỗi
        System.out.println("Processed VNPay return: Payment successful.");
        return ResponseEntity.ok(paymentOrder);
    }

    /**
     * Endpoint to handle payment success.
     * @param paymentId The ID of the payment.
     * @param paymentLinkId The payment link ID.
     * @param jwt The authorization token.
     * @return A response indicating the success of the payment.
     * @throws Exception If an error occurs during processing.
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        System.out.println("Handling payment success...");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Payment Link ID: " + paymentLinkId);
        System.out.println("JWT: " + jwt);

        ApiResponse response = new ApiResponse();
        return ResponseEntity.ok(response);
    }
}