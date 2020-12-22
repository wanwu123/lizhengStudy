package com.lizheng.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Success implements AuthenticationSuccessHandler {

    private String redirectUrl;
    public Success(String redirectUrl){
        this.redirectUrl=redirectUrl;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
    throws IOException,
            ServletException {
            response.sendRedirect(redirectUrl);
        }
}
