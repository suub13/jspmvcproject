package com.playdata.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DbConnectionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // web.xml 에서 context params로 지정해 준 값을 불러오기
        String dbUrl = sce.getServletContext().getInitParameter("DB_URL");
        String dbUser = sce.getServletContext().getInitParameter("DB_USER");
        String dbPassword = sce.getServletContext().getInitParameter("DB_PASSWORD");

        try {
            // Driver load
            Class.forName("org.mariadb.jdbc.Driver");

            // Connection 객체를 생성  - 우리가 넣어준 DB 데이터를 넣어서 DB 연결하기
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // 생성된 Connection 객체를 Servletcontext에 Attribute로 저장
            // "DB_USER" 과 그 값처럼 "conn"에 conn Connecction 객체를 저장함.
            sce.getServletContext().setAttribute("conn", conn);
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection conn = (Connection) sce.getServletContext().getAttribute("conn");
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
