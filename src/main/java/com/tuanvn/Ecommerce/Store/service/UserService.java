package com.tuanvn.Ecommerce.Store.service;

import com.tuanvn.Ecommerce.Store.modal.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
