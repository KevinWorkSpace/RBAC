package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.AjaxResult;
import com.atguigu.atcrowdfunding.bean.Page;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object updateUser(User user) {
        AjaxResult result = new AjaxResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreatetime(sdf.format(new Date()));
            userService.updateUser(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/deletes")
    public Object deleteUsers(Integer[] userId) {
        AjaxResult result = new AjaxResult();
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("users", userId);
            userService.deleteUsers(map);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteuser(Integer id) {
        AjaxResult result = new AjaxResult();
        try {
            userService.deleteUser(id);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/edit")
    public String editUser(Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping("/assign")
    public String assign(Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.queryAll();
        model.addAttribute("roles", roles);
        return "user/assign";
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(User user) {
        AjaxResult result = new AjaxResult();
        try {
            user.setUserpswd("123456");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setCreatetime(sdf.format(new Date()));
            userService.insertUser(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object queryPage(Integer pageNo, Integer pageSize, String queryText) {
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("start", (pageNo-1) * pageSize);
            map.put("size", pageSize);
            map.put("queryText", queryText);
            List<User> users = userService.pageQueryData(map);
            int totalNo = userService.pageCount(map);
            int totalPage = 0;
            if (totalNo % pageSize == 0) {
                totalPage = totalNo / pageSize;
            }
            else {
                totalPage = totalNo / pageSize + 1;
            }
            Page<User> page = new Page<>();
            page.setDatas(users);
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

    @RequestMapping("/index")
    public String index(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "2") int pageSize, Model model) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("start", (pageNo-1) * pageSize);
//        map.put("size", pageSize);
//        List<User> users = userService.pageQueryData(map);
//        int totalNo = userService.pageCount(map);
//        int totalPage = 0;
//        if (totalNo % pageSize == 0) {
//            totalPage = totalNo / pageSize;
//        }
//        else {
//            totalPage = totalNo / pageSize + 1;
//        }
//        model.addAttribute("users", users);
//        model.addAttribute("curPage", pageNo);
//        model.addAttribute("totalPage", totalPage);
        return "user/index";
    }
}
