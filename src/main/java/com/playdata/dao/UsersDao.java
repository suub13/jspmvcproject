package com.playdata.dao;

import com.playdata.dto.UsersDto;
import org.mariadb.jdbc.Connection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    /**
     * users 테이블에서 username과 password가 일치하는 레코드를 조회한다.
     * @param username
     * @param password
     * @param req
     * @return usersDto
     */
    public UsersDto selectByUsernameAndPassword(String username, String password, HttpServletRequest req) {
        Connection conn = (Connection) req.getServletContext().getAttribute("conn");
        String sql = "SELECT * FROM users WHERE username = ? AND password=?";
        UsersDto usersDto = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //로그인 성공
                // Session 에 username 속성을 추가
//                req.getSession().setAttribute("username", username);;
                usersDto = new UsersDto(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("login_fail_count"),
                        rs.getString("created_at")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usersDto;
    }

    public void updateLoginFailedCount(String username, int loginFailCount, HttpServletRequest req){
        String sql = "UPDATE users SET login_fail_count = ? WHERE username = ?";
        Connection conn = (Connection) req.getServletContext().getAttribute("conn");
        try (PreparedStatement pstmt = conn.prepareStatement (sql)){
            pstmt. setInt (1, loginFailCount);
            pstmt. setString(2, username);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace ();
        }
    }

    public UsersDto selectByUsername(String username, HttpServletRequest req) {
        Connection conn = (Connection) req.getServletContext().getAttribute("conn");
        String sql = "SELECT * FROM users WHERE username = ?";
//        System.out.println("username = " + username);
        UsersDto usersDto = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //로그인 성공
                // Session 에 username 속성을 추가
//                req.getSession().setAttribute("username", username);;
                usersDto = new UsersDto(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("login_fail_count"),
                        rs.getString("created_at")
                );
//                System.out.println("selectbyusername");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usersDto;
    }

    public void registerUser(String username, String password, HttpServletRequest req){
        String sql = "INSERT INTO users(username, password) values(?, ?)";
        java.sql.Connection conn = (java.sql.Connection) req.getServletContext().getAttribute("conn");
        try (PreparedStatement pstmt = conn.prepareStatement (sql)){
            pstmt. setString (1, username);
            pstmt. setString(2, password);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace ();
        }
    }


}
