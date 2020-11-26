package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> queryAll() {
        return userDao.queryAll();
    }

    public User query4login(User user) {
        return userDao.query4login(user);
    }

    public List<User> pageQueryData(Map<String, Object> map) {
        return userDao.pageQueryData(map);
    }

    public int pageCount(Map<String, Object> map) {
        return userDao.pageCount(map);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
}
