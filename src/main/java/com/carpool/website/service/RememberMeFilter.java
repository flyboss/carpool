package com.carpool.website.service;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by deado on 2016/12/19.
 */
public class RememberMeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //filterChain.doFilter(request,response);


        boolean loginRequest = false;
        boolean resourceRequest = false;
        boolean rememberCookieExist = false;
        boolean cookiesExist = false;
        String uri = request.getRequestURI();

        if (0 == uri.compareTo("/")) {
            filterChain.doFilter(request, response);
            return;
        }

        //check if login request
        loginRequest = uri.contains("login") ;

        resourceRequest = uri.contains("static") || uri.contains("ico") || uri.contains("getLoginCode") || uri.contains("checkimagecode")
                || uri.contains("index");
        //check cookies
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            cookiesExist = true;
            for (Cookie cookie : cookies) {
                if (0 == cookie.getName().compareTo("remember-me")) {
                    rememberCookieExist = true;
                    break;
                }
            }

        }

        //如果只是评论某一个用户的话，并且带有
        //就不进行安全验证，如果进行安全验证的话，这部分就会出现问题
        if(uri.contains("comment/makeComment"))
            request.getRequestDispatcher(uri).forward(request,response);
        //do filter

        if( resourceRequest ||(loginRequest&&!rememberCookieExist) || (!loginRequest&&cookiesExist&&rememberCookieExist) ){
            filterChain.doFilter(request,response);
        }else if( !loginRequest && !rememberCookieExist ){

            //by shareLink,then store shareLink in Session
            if(uri.contains("room/detail"))
            {
                String roomId = request.getParameter("roomId");
                request.getSession().setAttribute("shareLink",uri+"?roomId="+roomId);
            }
            response.sendRedirect("/login");
        }
        else {
            response.sendRedirect("/home/main");
        }
    }
}
