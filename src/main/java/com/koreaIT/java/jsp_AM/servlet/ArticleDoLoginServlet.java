package com.koreaIT.java.jsp_AM.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.koreaIT.java.jsp_AM.util.DBUtil;
import com.koreaIT.java.jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/doLogin")
public class ArticleDoLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// DB 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 x");
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/24_08_JAM";

		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			SecSql sql = SecSql.from("SELECT COUNT(*) > 0");
	        sql.append("FROM `member`");
	        sql.append("WHERE loginId = ?", id);
	        sql.append("AND loginPw = ?;", pw);
	        boolean iduse = DBUtil.selectRowBooleanValue(conn, sql);
	        
	        if(!iduse) {
	        	response.getWriter().append("<script>alert('아이디 및 비밀번호 오류입니다.'); location.replace('login');</script>");
	        	return;
	        }


			response.getWriter()
					.append(String.format("<script>alert('%s님 로그인 되셨습니다.'); location.replace('../home/loginMain');</script>", id));

		} catch (SQLException e) {
			System.out.println("에러 1 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}