package com.playdata.web;


import com.playdata.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/logout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersService usersService = new UsersService();
        usersService.logout(req);
        resp.sendRedirect("/index?logout=1");

//        if (loginResult == UsersService.LOGIN_SUCCESS){
//            //로그인 성공
//            resp.sendRedirect("/index");
//        } else if(loginResult == UsersService.LOGIN_FAIL_USER_NOT_FOUND){
//            //사용자 찾을 수 없음
//            resp.sendRedirect("/login?error=1");
//        } else {
//            //로그인 실패
//            resp.sendRedirect("/login?error=2");
//        }
    }
}
