package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleDao {

    List<Role> pageQueryData(Map<String, Object> map);

    int pageCount(Map<String, Object> map);

    void deleteRole(Integer id);

    @Select("select * from t_role where id = #{id}")
    Role queryById(Integer id);

    List<Role> queryAll();
}
