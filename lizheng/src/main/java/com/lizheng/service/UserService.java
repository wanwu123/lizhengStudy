package com.lizheng.service;



import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;


public interface UserService {



    public void exportAllExcel(HttpServletResponse response);


    public void up(long id);
}