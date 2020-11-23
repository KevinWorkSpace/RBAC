package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
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