package com.prd.approval.dao;


import com.prd.approval.entity.User;

public interface UserDAO {

    User selectUserByIdAndPassword(User user);
}
