package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

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

    @ResponseBody
    @RequestMapping("/doAjaxLogin")
    public Object doAjaxLogin(User user, HttpSession session) {
        User dbuser = userService.query4login(user);
        AjaxResult result = new AjaxResult();
        if (dbuser != null) {
            result.setSuccess(true);
            session.setAttribute("loginUser", dbuser);
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
