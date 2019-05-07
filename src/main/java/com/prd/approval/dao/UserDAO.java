package com.prd.approval.dao;


import com.prd.approval.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    Map<String,String> selectUserByIdAndPassword(User user);

    List<User> selectAllUser();
}
