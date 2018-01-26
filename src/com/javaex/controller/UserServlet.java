package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user ����");
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		if ("joinform".equals(actionName)) {
			System.out.println("joinform ����");
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");

		} else if ("join".equals(actionName)) {
			System.out.println("join ����");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo userVo = new UserVo();

			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);

			System.out.println(userVo.toString());

			UserDao userDao = new UserDao();
			userDao.insert(userVo);

			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");

		} else if ("loginform".equals(actionName)) {
			System.out.println("loginform ����");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");

		} else if ("login".equals(actionName)) {
			System.out.println("login ����");

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			System.out.println(email + "/" + password);

			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);

			if (userVo == null) {
				System.out.println("�α��� �����߽��ϴ�.");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			} else {
				System.out.println("�α��� �����Դϴ�.");

				HttpSession session = request.getSession(); // HttpSession�� �ʿ���
				session.setAttribute("authUser", userVo);

				WebUtil.redirect(request, response, "/mysite/main");
			}

		} else if ("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, "/mysite/main");
			System.out.println("logout �Ǿ����ϴ�.");

		} else if ("modifyform".equals(actionName)) {
			System.out.println("modifyform ����");

			UserDao udao = new UserDao();
			UserVo uvo = new UserVo();

			HttpSession session = request.getSession();
			UserVo vo = (UserVo) session.getAttribute("authUser");

			int haha = vo.getNo();
			UserVo userVo = udao.getUser(haha);

			request.setAttribute("authUser", userVo);
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");

		} else if ("modify".equals(actionName)) {
			System.out.println("modify ����");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			if (authUser == null) { // ��α��� ����
				// �α��������� �̵�
			} else { // �α��� ����
				// vo(no, name, password, gender)
				int no = authUser.getNo();
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");

				UserVo uvo = new UserVo(no, name, "", password, gender);
				// dao.update(vo)
				UserDao udao = new UserDao();
				udao.modify(uvo);

				uvo.setNo(authUser.getNo());
				uvo.setName(name);
				uvo.setPassword(password);
				uvo.setGender(gender);
				System.out.println(uvo.toString());

				// session name ������
				session.setAttribute("authUser", uvo);

				// main �����̷�Ʈ
				WebUtil.redirect(request, response, "/mysite/main");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
