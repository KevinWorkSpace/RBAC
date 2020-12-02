package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        return "permission/index";
    }

    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData() {
        List<Permission> permissions = new ArrayList<>();
        List<Permission> ps = permissionService.queryAll();
        Map<Integer, Permission> map = new HashMap<>();
        for (Permission p : ps) {
            map.put(p.getId(), p);
        }
        for (Permission p : ps) {
            if (p.getPid() == 0) {
                permissions.add(p);
            }
            else {
                map.get(p.getPid()).getChildren().add(p);
            }
        }
        return permissions;
    }
}