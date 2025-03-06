package com.tuanvn.Ecommerce.Store.service;


import com.tuanvn.Ecommerce.Store.response.SignupRequest;

public interface AuthService {

    String createUser(SignupRequest req);

}
