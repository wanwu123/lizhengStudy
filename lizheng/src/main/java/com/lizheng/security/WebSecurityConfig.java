package com.lizheng.security;


import com.lizheng.bean.po.User;
import com.lizheng.mapper.db1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@Configuration
//@EnableWebSecurity //springboot可以不用配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login").usernameParameter("username")
                .defaultSuccessUrl("/main.html")
        .successHandler(new Success("/main.html"))
        .failureHandler(new Error("/error.html"));
        //不用认证
        http.authorizeRequests().antMatchers("/login.html","/user/login").permitAll()
                .anyRequest().authenticated();//认证


        //
        http.csrf().disable();

    }

    @Resource
    private UserMapper userMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String password=passwordEncoder().encode("123456");
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication().passwordEncoder(passwordEncoder());
        List<User> users = userMapper.selectAllUser();
        for (User user : users) {
            authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer.withUser(user.getName()).password(password).roles("admin");
        }
//        String password=passwordEncoder().encode("123456");
//        auth
//        //使用基于内存的InMemoryUserDetailsManager
//        .inMemoryAuthentication()
//        //使用PasswordEncoder密码编码器
//        //.passwordEncoder(passwordEncoder())
//        //配置用户
//        .withUser("lizheng2").password(password).roles("admin")
//        //配置其他用户
//        .and()
//        .withUser("lizheng3").password(password).roles("user");
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        return new UserDetailsService(){
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                String pw = passwordEncoder().encode("123456");
//                UserDetails fox  = new User("fox", pw,
//                        AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
//                return fox;
//            }
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
