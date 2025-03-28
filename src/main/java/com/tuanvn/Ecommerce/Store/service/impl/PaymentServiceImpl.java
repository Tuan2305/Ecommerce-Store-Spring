package com.tuanvn.Ecommerce.Store.service.impl;

import com.tuanvn.Ecommerce.Store.domain.PaymentMethod;
import com.tuanvn.Ecommerce.Store.domain.PaymentOrderStatus;
import com.tuanvn.Ecommerce.Store.modal.Order;
import com.tuanvn.Ecommerce.Store.modal.PaymentOrder;
import com.tuanvn.Ecommerce.Store.modal.User;
import com.tuanvn.Ecommerce.Store.repository.PaymentOrderRepository;
import com.tuanvn.Ecommerce.Store.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Value("${vnpay.tmnCode}")
    private String vnpTmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnpHashSecret;

    @Value("${vnpay.paymentUrl}")
    private String vnpPaymentUrl;

    @Value("${vnpay.returnUrl}")
    private String vnpReturnUrl;

    @Override
    public String createVNPAYPaymentUrl(Long orderId, HttpServletRequest request) throws Exception {
        PaymentOrder order = paymentOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("PaymentOrder not found"));

        String vnp_TxnRef = String.valueOf(order.getId());
        String vnp_IpAddr = request.getRemoteAddr();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnpTmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(order.getAmount() * 100)); // Nhân 100 cho VNPAY
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang #" + order.getId());
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnpReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", String.format("%tY%<tm%<td%<tH%<tM%<tS", new Date()));

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String field : fieldNames) {
            String value = vnp_Params.get(field);
            if (value != null && !value.isEmpty()) {
                hashData.append(field).append("=").append(value).append("&");
                query.append(URLEncoder.encode(field, StandardCharsets.UTF_8.name()))
                        .append("=").append(URLEncoder.encode(value, StandardCharsets.UTF_8.name())).append("&");
            }
        }
        hashData.setLength(hashData.length() - 1);
        query.setLength(query.length() - 1);

        String vnp_SecureHash = hmacSHA512(vnpHashSecret, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        // Lưu paymentMethod là VNPAY
        order.setPaymentMethod(PaymentMethod.VNPAY); // Giả sử PaymentMethod.VNPAY tồn tại
        paymentOrderRepository.save(order);

        return vnpPaymentUrl + "?" + query.toString();
    }

    @Override
    public PaymentOrder processVNPAYReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (String key : request.getParameterMap().keySet()) {
            String value = request.getParameter(key);
            if (value != null && !value.isEmpty()) {
                fields.put(key, value);
            }
        }

        String vnp_SecureHash = fields.remove("vnp_SecureHash");
        String signValue = hashAllFields(fields);

        Long orderId = Long.parseLong(fields.get("vnp_TxnRef"));
        PaymentOrder order = paymentOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("PaymentOrder not found"));

        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(fields.get("vnp_ResponseCode"))) {
                order.setStatus(PaymentOrderStatus.SUCCESS);
                order.setPaymentLinkId(fields.get("vnp_TransactionNo")); // Lưu mã giao dịch VNPAY
            } else {
                order.setStatus(PaymentOrderStatus.FAILED);
            }
        } else {
            order.setStatus(PaymentOrderStatus.FAILED); // Hoặc thêm trạng thái INVALID_SIGNATURE nếu enum hỗ trợ
        }

        return paymentOrderRepository.save(order);
    }

    @Override
    public PaymentOrder createPaymentOrder(Long amount, String orderInfo, User user, Set<Order> orders) {
        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setStatus(PaymentOrderStatus.PENDING);
        order.setUser(user);
        order.setOrders(orders);
        return paymentOrderRepository.save(order);
    }

    private String hmacSHA512(String key, String data) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA512");
        mac.init(new javax.crypto.spec.SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512"));
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    private String hashAllFields(Map<String, String> fields) {
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        for (String field : fieldNames) {
            String value = fields.get(field);
            if (value != null && !value.isEmpty()) {
                sb.append(field).append("=").append(value).append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        try {
            return hmacSHA512(vnpHashSecret, sb.toString());
        } catch (Exception e) {
            return "";
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
