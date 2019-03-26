/**
 * Author: lin
 * Date: 2019/3/5 18:31
 */
package com.prd.approval.service.impl;


import com.prd.approval.dao.UserDAO;
import com.prd.approval.entity.User;
import com.prd.approval.service.UserService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseUtil<User> login(User webUser) {

        if (webUser == null
                || webUser.getId() == null || webUser.getId().isEmpty()
                || webUser.getPassword() == null || webUser.getPassword().isEmpty()
        ) {
            return new ResponseUtil<>(0, "账号和密码不能为空");
        }
        User user = userDAO.selectUserByIdAndPassword(webUser);

        if (user == null) {
            return new ResponseUtil<>(0, "账号或密码错误");
        }

        return new ResponseUtil<>(1, "登陆成功");


    }
}
