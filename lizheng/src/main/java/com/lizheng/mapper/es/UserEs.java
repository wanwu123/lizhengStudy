package com.lizheng.mapper.es;

import com.lizheng.bean.po.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserEs extends ElasticsearchRepository<User,Long> {
}
