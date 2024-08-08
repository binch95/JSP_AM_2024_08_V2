package com.koreaIT.java.jsp_AM.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.koreaIT.java.jsp_AM.service.ArticleService;
import com.koreaIT.java.jsp_AM.util.DBUtil;
import com.koreaIT.java.jsp_AM.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;

		this.articleService = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int itemsInAPage = 10;
		int limitFrom = (page - 1) * itemsInAPage;
		int totalCnt = articleService.getTotalCnt();
		int totalPage = (int) Math.ceil(totalCnt / (double) itemsInAPage);


		List<Map<String, Object>> articleRows = articleService.getForPrintArticles(limitFrom, itemsInAPage);

		HttpSession session = request.getSession();

		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("articleRows", articleRows);
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void DoWrite() throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");

		SecSql sql = SecSql.from("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("memberId = ?,", loginedMemberId);
		sql.append("title = ?,", title);
		sql.append("`body` = ?;", body);

		int id = DBUtil.insert(conn, sql);

		response.getWriter()
				.append(String.format("<script>alert('%d번 글이 등록 됨'); location.replace('list');</script>", id));
		
	}

}