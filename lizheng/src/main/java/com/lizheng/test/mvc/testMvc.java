package com.lizheng.test.mvc;

import org.apache.catalina.startup.Tomcat;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class testMvc implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) {
        Tomcat tomcat = new Tomcat();

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(testMvc.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }

    public static void main(String[] args) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        List<Boolean> collect = booleans.stream().filter(e -> !e).collect(Collectors.toList());
        System.out.println(collect.size());
    }
}