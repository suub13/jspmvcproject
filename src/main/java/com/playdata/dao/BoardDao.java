package com.playdata.dao;

import com.playdata.dto.BoardDto;
import org.mariadb.jdbc.Connection;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * board 테이블에서 모든 데이터를 조회
 * @param req
 * @return List<BoardDto>
 */
public class BoardDao {
    public List<BoardDto> selectAll(HttpServletRequest req){
        Connection conn = (Connection)req.getServletContext().getAttribute("conn");
        List<BoardDto> boardList = new ArrayList<>();

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM board ORDER BY id DESC");
            while(rs.next()){
                BoardDto boardDto = new BoardDto(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("author"),
                        rs.getString("created_at")
                );
                boardList.add(boardDto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return boardList;
    }
}
