package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("servlet 진입");
		String actionName = request.getParameter("a");

		if ("list".equals(actionName)) {
			System.out.println("list진입");
			GuestBookDao dao = new GuestBookDao();

			List<GuestBookVo> list = dao.getList();
			request.setAttribute("list", list);
			System.out.println(list.toString());

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");

		} else if ("add".equals(actionName)) {
			System.out.println("add 진입");
			request.setCharacterEncoding("UTF-8");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);

			System.out.println(vo.toString());

			GuestBookDao dao = new GuestBookDao();
			dao.insert(vo);

			WebUtil.redirect(request, response, "/mysite/gb?a=list");
		} else if ("deleteform".equals(actionName)) {
			System.out.println("deleteform 진입");

			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");

		} else if ("delete".equals(actionName)) {
			System.out.println("delete 진입");
			request.setCharacterEncoding("UTF-8");

			String password = request.getParameter("password");

			GuestBookDao dao = new GuestBookDao();
			int no = Integer.parseInt(request.getParameter("no").trim());

			GuestBookVo vo = dao.select(no);
			
			if (vo.getPassword().equals(password)) {
				dao.delete(no);
				WebUtil.redirect(request, response, "/mysite/gb?a=list");
			} else {
				System.out.println("잘못되었습니다.");
			}
		}else {
			System.out.println("잘못된 a값입니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
