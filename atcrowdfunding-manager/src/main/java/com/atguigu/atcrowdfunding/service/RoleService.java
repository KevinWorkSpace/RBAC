package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> pageQueryData(Map<String, Object> map);

    int pageCount(Map<String, Object> map);

    void deleteRole(Integer id);

    Role queryById(Integer id);

    void deleteRoles(HashMap<String, Object> map);

    List<Role> queryAll();

    void insertRolePermission(Map<String, Object> map);
}
