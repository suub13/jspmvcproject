package com.playdata.web;

import com.playdata.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
//        System.out.println("username = " + username);
        String password = req.getParameter("password");
        UsersService usersService = new UsersService();
        int loginResult = usersService.login(username, password, req);
        if (loginResult == UsersService.LOGIN_SUCCESS){
            //로그인 성공

            resp.sendRedirect("/index");
        } else if(loginResult == UsersService.LOGIN_FAIL_USER_NOT_FOUND){
            //사용자 찾을 수 없음
            resp.sendRedirect("/login?error=1");
        } else {
            //로그인 실패
            resp.sendRedirect("/login?error=2");
        }
        
    }
}
