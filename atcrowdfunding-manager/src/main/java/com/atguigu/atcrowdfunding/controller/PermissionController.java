package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(Permission permission) {
        AjaxResult result = new AjaxResult();
        try {
            permissionService.insertPermission(permission);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Permission permission) {
        AjaxResult result = new AjaxResult();
        try {
            permissionService.deletePermission(permission);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/add")
    public String add() {
        return "permission/add";
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Permission permission = permissionService.queryById(id);
        model.addAttribute("permission", permission);
        return "permission/edit";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(Permission permission) {
        AjaxResult result = new AjaxResult();
        try {
            permissionService.updatePermission(permission);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/loadAssignData")
    public Object loadAssignData(Integer roleid) {
        List<Permission> permissions = new ArrayList<>();
        List<Permission> ps = permissionService.queryAll();
        List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);

        Map<Integer, Permission> map = new HashMap<>();
        for (Permission p : ps) {
            if (permissionids.contains(p.getId())) {
                p.setChecked(true);
            } else {
                p.setChecked(false);
            }
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