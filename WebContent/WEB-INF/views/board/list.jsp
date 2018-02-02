<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!--header -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
				
		<!--navigation-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="bs?a=list" method="post">
					<input type="text" id="kwd" name="searchValue" value="">
					<input type="submit" value="찾기">
				</form>

				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					<c:forEach items="${bList}" var="bVo" varStatus="status">
						<tr>
							<td>${bVo.no}</td>
							<td><a href="bs?a=view&no=${bVo.no}">${bVo.title}</a></td>
							<td>${bVo.userName}</td>
							<td>${bVo.hit}</td>
							<td>${bVo.regDate}</td>
							<td><c:if test="${bVo.userNo == authUser.no}">
								<a href="bs?a=delete&no=${bVo.no}&userNo=${bVo.userNo}" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
					
 					<!--<tr>
						<td>2</td>
						<td><a href="">두 번째 글입니다.</a></td>
						<td>정우성</td>
						<td>3</td>
						<td>2015-10-02 12:04:12</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td><a href="">첫 번째 글입니다.</a></td>
						<td>이효리</td>
						<td>3</td>
						<td>2015-09-25 07:24:32</td>
						<td><a href="" class="del">삭제</a></td>
					</tr> -->
				</table>
				<div class="pager">
					<ul>
						<c:if test="${currentPage-1>0}">
						<li><a href="bs?a=list&page=${currentPage-1}">◀</a></li>
						</c:if>
					
						<c:forEach var="i" begin="1" end="${lastPage}" step="1">
								<c:choose>
									<c:when test="${i == param.currentPage }">
										<li class="selected"><a href="bs?a=list&page=${currentPage}">${i}</a></li>
									</c:when>
									 <c:otherwise>
									 <c:if test="${i <= currentPage +2 && i >= currentPage -2}">
										<a href="bs?a=list&page=${i}">${i}</a>
									 </c:if>
									 </c:otherwise> 
								</c:choose>
						</c:forEach>
				
						<c:if test="${currentPage !=lastPage }">
						<li><a href="bs?a=list&page=${currentPage+1}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="/mysite/bs?a=write" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>			
	
		<!--footer-->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import> 
		
	</div>
</body>
</html>