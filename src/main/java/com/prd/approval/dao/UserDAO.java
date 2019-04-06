package com.prd.approval.dao;


import com.prd.approval.entity.User;

import java.util.List;

public interface UserDAO {

    User selectUserByIdAndPassword(User user);

    List<User> selectAllUser();
}
