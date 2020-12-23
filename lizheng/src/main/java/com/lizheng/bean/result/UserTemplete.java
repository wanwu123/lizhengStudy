package com.lizheng.bean.result;

import com.lizheng.bean.po.KuCun;
import lombok.Data;

import java.util.List;


@Data
public class UserTemplete {
    private Long id;
    private List<KuCun> kuCunList;
}
