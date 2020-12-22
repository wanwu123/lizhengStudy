package com.lizheng.mapper.db1;

import com.lizheng.bean.po.KuCun;
import org.apache.ibatis.annotations.Param;

public interface KuCunMapper {
    KuCun selectById(@Param("id")Long id);

    void count_one(@Param("id")Long id);

    int update2(@Param("id")Long id,@Param("num")Integer num);
}
