package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.Permission;

import java.util.List;

public interface PermissionService {
    Permission queryRootPermission();

    List<Permission> queryChildPermission(Integer id);

    List<Permission> queryAll();
}
