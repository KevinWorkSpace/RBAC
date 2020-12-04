package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.PermissionService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
//        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    @ResponseBody
    @RequestMapping("/doAjaxLogin")
    public Object doAjaxLogin(User user, HttpSession session) {
        User dbuser = userService.query4login(user);
        AjaxResult result = new AjaxResult();
        if (dbuser != null) {
            result.setSuccess(true);
            session.setAttribute("loginUser", dbuser);

            List<Permission> permissions = permissionService.queryPermissionsByUser(dbuser);
            Map<Integer, Permission> map = new HashMap<>();
            Permission root = null;
            Set<String> set = new HashSet<>();
            for (Permission permission : permissions) {
                map.put(permission.getId(), permission);
                if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                    set.add(session.getServletContext().getContextPath() + "/" + permission.getUrl());
                }
            }
            session.setAttribute("authUriSet", set);
            for (Permission permission : permissions) {
                if (permission.getPid() == 0) {
                    root = permission;
                }
                else {
                    Permission parent = map.get(permission.getPid());
                    parent.getChildren().add(permission);
                }
            }
            session.setAttribute("rootPermission", root);
        }
        else result.setSuccess(false);
        return result;
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model) throws UnsupportedEncodingException {
        User dbuser = userService.query4login(user);
        if (dbuser != null) return "main";
        else {
            model.addAttribute("errorMsg", "帐号和密码不正确，请重新登陆");
            return "redirect:login";
        }
    }
}
