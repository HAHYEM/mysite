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
		System.out.println("user 진입");
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			System.out.println("joinform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		
		}else if("join".equals(actionName)) {
			System.out.println("join 진입");
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
			
		}else if("loginform".equals(actionName)) {
			System.out.println("loginform 진입");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			
		}else if("login".equals(actionName)) {
			System.out.println("login 진입");
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			System.out.println(email + "/" + password);
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("로그인 실패했습니다.");
				
			}else {
				System.out.println("로그인 성공입니다.");
				
				HttpSession session = request.getSession();	//HttpSession이 필요함
				session.setAttribute("authUser", userVo);
				
				WebUtil.redirect(request, response, "/mysite/main");
			}
			
		}else if("logout".equals(actionName)){
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			WebUtil.redirect(request, response, "/mysite/main");
			System.out.println("logout 되었습니다.");
			
		}else if("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
		
		}else if("modify".equals(actionName)) {
			System.out.println("modify 진입");
			UserDao udao = new UserDao();
			UserVo uvo = new UserVo();
			
			HttpSession session = request.getSession();
			UserVo vo = (UserVo)session.getAttribute("authUser");
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			
			uvo.setName(name);
			uvo.setEmail(email);
			uvo.setPassword(password);
			uvo.setGender(gender);
			uvo.setNo(vo.getNo());
			System.out.println(uvo.toString());
			udao.modify(uvo);
			
			response.sendRedirect("/mysite/main");
				//HttpSession이 필요함
			session.setAttribute("authUser", uvo);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
