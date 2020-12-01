package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> queryAll();

    User query4login(User user);

    List<User> pageQueryData(Map<String, Object> map);

    int pageCount(Map<String, Object> map);

    void insertUser(User user);

    User queryById(Integer id);

    void updateUser(User user);

    void deleteUser(Integer id);

    void deleteUsers(HashMap<String, Object> map);
}
