package com.lizheng.security;

import com.lizheng.mapper.db1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailServiece implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Stringhashpw=BCrypt.hashpw("123456",BCrypt.gensalt());
        UserDetails userDetails = User.withUsername("lizheng").
                password(passwordEncoder.encode("123456")).authorities("admin").build();
        //password("{noop}123456").authorities("admin").build();
        return userDetails;
    }

}
