package com.atguigu.atcrowdfunding.web;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Set<String> uriSet = new HashSet<>();
        List<Permission> permissions = permissionService.queryAll();
        for (Permission permission : permissions) {
            if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                uriSet.add(request.getSession().getServletContext().getContextPath() + "/" + permission.getUrl());
            }
        }
        if (uriSet.contains(uri)) {
            Set<String> set = (Set<String>) request.getSession().getAttribute("authUriSet");
            if (set.contains(uri)) {
                return true;
            }
            else {
                String path = request.getSession().getServletContext().getContextPath();
                response.sendRedirect(path + "/error");
                return false;
            }
        }
        else return true;
    }
}
