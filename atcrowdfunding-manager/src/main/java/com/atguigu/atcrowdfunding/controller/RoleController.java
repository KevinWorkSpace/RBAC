package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object queryPage(Integer pageNo, Integer pageSize, String queryText) {
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageNo-1) * pageSize);
            map.put("size", pageSize);
            map.put("queryText", queryText);
            List<Role> roles = roleService.pageQueryData(map);
            int totalNo = roleService.pageCount(map);
            int totalPage = 0;
            if (totalNo % pageSize == 0) {
                totalPage = totalNo / pageSize;
            }
            else {
                totalPage = totalNo / pageSize + 1;
            }
            Page<Role> page = new Page<>();
            page.setDatas(roles);
            page.setCurPage(pageNo);
            page.setTotalPage(totalPage);
            result.setData(page);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteRole(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            roleService.deleteRole(id);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/edit")
    public String editUser(Integer id, Model model) {
        Role role = roleService.queryById(id);
        model.addAttribute("role", role);
        return "role/edit";
    }

    @ResponseBody
    @RequestMapping("/deletes")
    public Object deleteRoles(Integer[] roleId) {
        AjaxResult result = new AjaxResult();
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("roles", roleId);
            roleService.deleteRoles(map);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/index")
    public String index() {
        return "role/index";
    }
}
