package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/bs")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("servlet 진입");
		String actionName = request.getParameter("a");

		if ("list".equals(actionName)) {
			System.out.println("list 진입");
			//페이징만들기 추가
			
			int page = 1;	//현재 페이지
			int eachPage = 5;
			int totalCount = 0;
			int lastPage = 0;
			if(request.getParameter("page") != null) {
			page =Integer.valueOf(request.getParameter("page"));
			}
			
			

			BoardDao dao = new BoardDao();
			List<BoardVo> bList = dao.getList(page,eachPage);
			totalCount = dao.countList();
			if(totalCount % eachPage != 0) {
				lastPage = (totalCount / eachPage) +1;
			}else {
				lastPage = (totalCount / eachPage);
			}
			
			//검색기능 추가
/*			if(searchValue != null) {
				bList = dao.getList(searchValue);
				request.setAttribute("bList", bList);
				WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
				return;
			}*/
			request.setAttribute("bList", bList);
			request.setAttribute("currentPage", page);
			request.setAttribute("lastPage", lastPage);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
		} else if ("modifyform".equals(actionName)) {
			System.out.println("modifyform 진입");
			int no = Integer.parseInt(request.getParameter("no"));
		
			BoardDao bdao = new BoardDao();
			BoardVo bvo = bdao.getModify(no);
			
			request.setAttribute("view", bvo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");

		} else if ("modify".equals(actionName)) {
			System.out.println("modify 진입");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao bdao = new BoardDao();
			bdao.modify(title,content, no);
			
			BoardVo bvo = bdao.view(no);

			request.setAttribute("view", bvo);
			WebUtil.redirect(request, response, "/mysite/bs?a=view&no=" + no);

		} else if ("view".equals(actionName)) {
			System.out.println("view 진입");
			int no = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			
			dao.hitUpdate(no);
			
			BoardVo vo = dao.view(no);

			request.setAttribute("view", vo);
			System.out.println(vo.toString());
			WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

		} else if ("write".equals(actionName)) {
			System.out.println("write 진입");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			if (authUser == null) {
				WebUtil.forward(request, response, "/WEB-INF/views/users/loginform.jsp");
			} else {
				WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
			}
		} else if ("writer".equals(actionName)) {
			System.out.println("writer 진입");
			request.setCharacterEncoding("UTF-8");

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setUserNo(authUser.getNo());

			System.out.println(vo.toString());

			BoardDao dao = new BoardDao();
			dao.write(vo);

			WebUtil.redirect(request, response, "/mysite/bs?a=list");
			
		} else if ("delete".equals(actionName)) {
			System.out.println("delete 진입");
			request.setCharacterEncoding("UTF-8");

			BoardDao dao = new BoardDao();
			int no = Integer.parseInt(request.getParameter("no"));
			dao.delete(no);
			
			WebUtil.redirect(request, response, "/mysite/bs?a=list");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
