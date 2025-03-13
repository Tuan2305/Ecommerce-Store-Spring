package com.tuanvn.Ecommerce.Store.controller;

import com.tuanvn.Ecommerce.Store.modal.Seller;
import com.tuanvn.Ecommerce.Store.modal.VerificationCode;
import com.tuanvn.Ecommerce.Store.repository.VerificationCodeRepository;
import com.tuanvn.Ecommerce.Store.request.LoginOtpRequest;
import com.tuanvn.Ecommerce.Store.request.LoginRequest;
import com.tuanvn.Ecommerce.Store.response.ApiResponse;
import com.tuanvn.Ecommerce.Store.response.AuthResponse;
import com.tuanvn.Ecommerce.Store.service.AuthService;
import com.tuanvn.Ecommerce.Store.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")

public class SellerController {

    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;

    public SellerController(SellerService sellerService, VerificationCodeRepository verificationCodeRepository, AuthService authService) {
        this.sellerService = sellerService;
        this.verificationCodeRepository = verificationCodeRepository;
        this.authService = authService;
    }


//    @PostMapping("/sent/login-otp")
//    public ResponseEntity<ApiResponse> sentOtpHandler(
//            @RequestBody VerificationCode req) throws Exception {
//
//        authService.sentLoginOtp(req.getEmail());
//
//        ApiResponse res = new ApiResponse();
//
//        res.setMessage("otp sent successfully");
//
//        return ResponseEntity.ok(res);
//
//    }


  
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(
            @RequestBody LoginRequest req
            ) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
//            throw new Exception("wrong otp ..");
//        }

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);

        return  ResponseEntity.ok(authResponse);
    }
}
