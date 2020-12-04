package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.dao.PermissionDao;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission queryRootPermission() {
        return permissionDao.queryRootPermission();
    }

    @Override
    public List<Permission> queryChildPermission(Integer id) {
        return permissionDao.queryChildPermission(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionDao.queryAll();
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public Permission queryById(Integer id) {
        return permissionDao.queryById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Permission permission) {
        permissionDao.deletePermission(permission);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
        return permissionDao.queryPermissionidsByRoleid(roleid);
    }

    @Override
    public List<Permission> queryPermissionsByUser(User dbuser) {
        return permissionDao.queryPermissionsByUser(dbuser);
    }
}
