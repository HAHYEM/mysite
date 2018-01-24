<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "com.javaex.dao.GuestBookDao" %>
<%@ page import= "com.javaex.vo.GuestBookVo" %>
<%@ page import= "java.util.List" %>
<%
	GuestBookDao dao = new GuestBookDao();
	List<GuestBookVo> gList = dao.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
				
				<!-- 로그인 후 -->
				<!-- 
				<li><a href="">회원정보수정</a></li>
				<li><a href="">로그아웃</a></li> 
				<li> 황일영님 안녕하세요^^;</li>
				-->
			</ul>
		</div> <!-- /header -->
		
		<div id="navigation">
			<ul>
				<li><a href="">황일영</a></li>
				<li><a href="/mysite/gb?a=list">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form method="get" action="/mysite/gb">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " />
								<input type = "hidden" name="a" value="add"></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
							<%
								for(GuestBookVo vo : gList){
							%>
								<table width=510 border=1>
									<tr>
										<td><%=vo.getNo() %></td>
										<td><%=vo.getName() %></td>
										<td><%=vo.getRegDate() %></td>
										<td><a href="/mysite/gb?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
									</tr>
									<tr>
										<td colspan=4><%=vo.getContent() %></td>
									</tr>
								</table>
		   					 <br/>
							<%
								}
							%>
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>