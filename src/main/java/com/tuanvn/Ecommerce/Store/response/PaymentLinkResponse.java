package com.tuanvn.Ecommerce.Store.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;

}
