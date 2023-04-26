package com.playdata.service;

import com.playdata.dao.BoardDao;
import com.playdata.dto.BoardDto;
import org.mariadb.jdbc.Connection;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BoardService {
    /**
     * board 테이블에서 모든 데이터를 조회
     * @param req
     * @return List<BoardDto>
     */
    public List<BoardDto> getBoards(HttpServletRequest req) {
        BoardDao boardDao = new BoardDao();
        return boardDao.selectAll(req);
    }

    public BoardDto getBoard(String id, HttpServletRequest req) {
        BoardDao boardDao = new BoardDao();
        return boardDao.selectById(id, req);
    }
}
