package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> pageQueryData(Map<String, Object> map) {
        return roleDao.pageQueryData(map);
    }

    @Override
    public int pageCount(Map<String, Object> map) {
        return roleDao.pageCount(map);
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Role queryById(Integer id) {
        return roleDao.queryById(id);
    }

    @Override
    public void deleteRoles(HashMap<String, Object> map) {
        Integer[] roles = (Integer[]) map.get("roles");
        for (int role : roles) {
            roleDao.deleteRole(role);
        }
    }

    @Override
    public List<Role> queryAll() {
        return roleDao.queryAll();
    }

    @Override
    public void insertRolePermission(Map<String, Object> map) {
        roleDao.deleteRolePermissions(map);
        roleDao.insertRolePermission(map);
    }
}
