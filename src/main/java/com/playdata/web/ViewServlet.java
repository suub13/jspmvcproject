package com.playdata.web;

import com.playdata.dto.BoardDto;
import com.playdata.service.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BoardService service = new BoardService();

        // 데이터베이스에서 id에 해당하는 데이터를 조회
        BoardDto board = service.getBoard(id, req);

        //조회한 데이터를 request에 저장
        req.setAttribute("board", board);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/view.jsp");
        dispatcher.forward(req,resp);
    }
}
