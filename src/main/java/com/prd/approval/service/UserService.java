package com.prd.approval.service;


import com.prd.approval.entity.Message;
import com.prd.approval.entity.User;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;

public interface UserService {

    ResponseUtil<User> login(User webUser);

    ResponseUtil<List<Message>> getMessage();

}
