package com.prd.approval.service;


import com.prd.approval.entity.User;
import com.prd.approval.utils.ResponseUtil;

public interface UserService {

    ResponseUtil<User> login(User webUser);


}
