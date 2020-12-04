package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;

import java.util.List;

public interface PermissionService {
    Permission queryRootPermission();

    List<Permission> queryChildPermission(Integer id);

    List<Permission> queryAll();

    void insertPermission(Permission permission);

    Permission queryById(Integer id);

    void updatePermission(Permission permission);

    void deletePermission(Permission permission);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);

    List<Permission> queryPermissionsByUser(User dbuser);
}
