package com.be24.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/board/register"})
public class BoardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 데이터를 받아오는 부분
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        // db에 저장하는 부분
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mariadb://10.10.10.30:3306/test", "root", "qwer1234");
            // SQL 실행 및 결과를 받아오는 객체 생성하는 코드
            try (Statement stmt = conn.createStatement()) {

                Integer affectedRows = stmt.executeUpdate(
                        "INSERT INTO board (title, contents) VALUES ('" + title + "','" + contents + "')",
                        Statement.RETURN_GENERATED_KEYS
                );
                if(affectedRows > 0) {
                    // 게시글 Dto를 반환하게 변경해보시오.
                    ResultSet rs = stmt.getGeneratedKeys();
                    if(rs.next()) {
                        System.out.println("저장한 게시글의 idx : " + rs.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // 응답하는 부분
        resp.getWriter().write("잘 저장됨");
    }
}
