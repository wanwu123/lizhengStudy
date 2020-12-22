package com.lizheng.mapper.db2;

import com.lizheng.bean.res.UserListRes;

import java.util.List;

public interface UserInfoExtMapper {



    List<UserListRes> selectRiders();

    List<UserListRes> selectList();

    void update(long id);
}
