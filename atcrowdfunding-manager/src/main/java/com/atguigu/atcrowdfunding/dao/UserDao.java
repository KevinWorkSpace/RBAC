package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    @Select("select * from t_user")
    List<User> queryAll();

    @Select("select * from t_user where loginacct = #{loginacct} and userpswd = #{userpswd}")
    User query4login(User user);

    List<User> pageQueryData(Map<String, Object> map);

    int pageCount(Map<String, Object> map);

    void insertUser(User user);

    @Select("select * from t_user where id = #{id}")
    User queryById(Integer id);

    void updateUser(User user);

    void deleteUser(Integer id);

    void deleteUserRoles(Map<String, Object> map);

    void insertUserRoles(Map<String, Object> map);

    @Select("select roleid from t_user_role where userid = #{userid}")
    List<Integer> queryRoleidsByUserid(Integer id);
}
