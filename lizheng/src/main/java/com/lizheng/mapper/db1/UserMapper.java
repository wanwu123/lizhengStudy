package com.lizheng.mapper.db1;


import com.lizheng.annotation.Select;
import com.lizheng.bean.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;



public interface UserMapper {

   User selectUser(@Param("id")Long id);

    User selectUserByName(@Param("userName")String userName);

    List<User> selectAllUser();

    @Select("select * from user where id = #{id}")
    String selectId(Long id);

    User getUser(@Param("id")Long id);
}