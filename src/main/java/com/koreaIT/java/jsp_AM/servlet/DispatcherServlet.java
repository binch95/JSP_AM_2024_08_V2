package com.koreaIT.java.jsp_AM.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.koreaIT.java.jsp_AM.controller.ArticleController;
import com.koreaIT.java.jsp_AM.util.DBUtil;
import com.koreaIT.java.jsp_AM.util.SecSql;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
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

		HttpSession session = request.getSession();

		boolean isLogined = false;
		int loginedMemberId = -1;
		Map<String, Object> loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
		}

		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);
		
		String requestUri = request.getRequestURI();
		
		String[] reqUriBits= requestUri.split("/");
		
		String controllerName = reqUriBits[3];
		String actionMethodName = reqUriBits[4]; 
		
		if(reqUriBits.length < 5) {
			response.getWriter().append(String.format("<script>alert('올바른 요청 x'); </script>"));
			return;
		}

		if(controllerName.equals("article")) {
			ArticleController articleController = new ArticleController(request, response, conn);
			if(reqUriBits[4].equals("list")) {
			articleController.showList();
			} else if(reqUriBits[4].equals("write")) {
				articleController.DoWrite();
			}
		}
		
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
