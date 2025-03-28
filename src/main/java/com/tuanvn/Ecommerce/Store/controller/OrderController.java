package com.tuanvn.Ecommerce.Store.controller;


import com.tuanvn.Ecommerce.Store.domain.PaymentMethod;
import com.tuanvn.Ecommerce.Store.modal.*;
import com.tuanvn.Ecommerce.Store.response.PaymentLinkResponse;
import com.tuanvn.Ecommerce.Store.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    public OrderController(OrderService orderService, UserService userService, CartService cartService, SellerService sellerService, SellerReportService sellerReportService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
        this.sellerService = sellerService;
        this.sellerReportService = sellerReportService;
    }

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(

            @RequestBody Address spippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart=cartService.findUserCart(user);
        Set<Order> orders =orderService.createOrder (user, spippingAddress, cart);

//        PaymentOrder paymentOrder=paymentService.createOrder (user, orders);
        PaymentLinkResponse res = new PaymentLinkResponse();
//
//        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
//            PaymentLink payment=paymentService.createRazorpayPaymentLink(user,
//                    paymentOrder.getAmount(),
//                    paymentOrder.getId());
//            String paymentUrl=payment.get("short_url");
//            String paymentUrlId=payment.get("id");
//            res.setPayment_link_url (paymentUrl);
//            res.setPayment_link_id(paymentUrlId);
//            paymentOrder.setPaymentLinkId (paymentUrlId);
//            paymentOrderRepository.save(paymentOrder);
//        }
//        else{
//            String paymentUrl = paymentService
//        }
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(
            @RequestHeader("Authorization")
            String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")

    public ResponseEntity< Order> getOrderById(@PathVariable Long orderId,@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order orders=orderService.findOrderById(orderId);
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);

    }
    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long orderItemId, @RequestHeader("Authorization")
            String jwt) throws Exception {
        System.out.println("--controller ");
        User user = userService.findUserByJwtToken(jwt) ;
        OrderItem orderItem=orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{orderId}/cancel")

    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId, user);
//        Seller seller= sellerService.getSellerById(order.getSellerId());
//        SellerReport report=sellerReportService.getSellerReport (seller);
//        report.setCanceledOrders (report.getCanceledOrders()+1);
//        report.setTotalRefunds (report.getTotalRefunds()+order.getTotalSelling Price());
//        sellerReportService.updateSellerReport (report);
        return ResponseEntity.ok(order);
    }

}
