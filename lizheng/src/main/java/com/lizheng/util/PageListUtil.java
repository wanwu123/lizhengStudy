package com.lizheng.util;

import com.lizheng.bean.page.Page;
import com.lizheng.bean.page.PageResult;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.parser.Cookie;


import java.util.ArrayList;
import java.util.List;

/**
 * @author lizheng
 * @param <T> 业务类型
 */

public class PageListUtil<T> {

    public PageResult<T> emptyRiderList(Integer page, Integer size){
        PageResult<T> res = new PageResult<>();
        res.setList(new ArrayList());
        Page pegeInfo = new Page();
        pegeInfo.setPageNum(page);
        pegeInfo.setPageSize(size);
        pegeInfo.setTotalCount(0L);
        res.setPage(pegeInfo);
        return res;
    }

    public PageResult<T> getRes(List<T> list, Integer page, Integer size, Long count){
        PageResult<T> res = new PageResult<>();
        res.setList(list);
        Page pegeInfo = new Page();
        pegeInfo.setPageNum(page);
        pegeInfo.setPageSize(size);
        pegeInfo.setTotalCount(count);
        res.setPage(pegeInfo);
        return res;
    }




}
