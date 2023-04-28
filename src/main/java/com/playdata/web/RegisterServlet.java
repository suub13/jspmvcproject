package com.playdata.web;

import com.playdata.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersService usersService = new UsersService();
        String username = req.getParameter("username");
//        System.out.println("username = " + username);
        String password = req.getParameter("password");
        int registerResult = usersService.register(username, password, req);

        if (registerResult == UsersService.REGISTER_SUCCESS){
            //회원가입 성공
            resp.sendRedirect("/login");
        } else if(registerResult == UsersService.REGISTER_ID_DUPLICATE){
            //사용자 아이디 중복
            resp.sendRedirect("/register?error=1");
        } else {
            //사용자 중복
            resp.sendRedirect("/register?error=2");
        }

    }
}
