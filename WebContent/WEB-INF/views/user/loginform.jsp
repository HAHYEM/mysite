<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("result");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
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
				<li><a href="">HAHYEM</a></li>
				<li><a href="/mysite/user?a=list.jsp">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->

		<div id="wrapper">
			<div id="content">
				<div id="user">
					
					<form id="login-form" name="loginform" method="get" action="/mysite/user">
						<input type="hidden" name="a" value="login" /> 
						
						<label class="block-label" for="email">이메일</label> 
						<input id="email" name="email" type="text" value=""> 

						<label class="block-label">패스워드</label> 
						<input name="password" type="password" value="">
						
								<%
									if("fail".equals(result)){
								%>
									<P>로그인이 실패했습니다.<br>다시 입력해주세요</P>
								<%
									}
								%>
	
						<input type="submit" value="로그인">
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017,2018</p>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->


</body>
</html>