<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/lr.min.css" />
<script src="../../js/jquery.min.js"></script>
</head>
<body style="border: 0px; padding: 0px; height: 400px;">
<c:choose>
	<c:when test="${fn:length(todos)==0}">你还没有写任何TODO...</c:when>
	<c:otherwise>
		<!-- 内容部分start -->
	<div class="row todos_Todos" style=" width: 700px;">
		<div id="todo_message">${message}</div>
			<ul>
				<c:forEach items="${todos}" var="todo" varStatus="status">
					<c:choose>
						<c:when test="${todo.isComplete eq true}">
							<li class="todos_Complete"><span>${todo.content}</span>&nbsp;&nbsp;&nbsp;
								<a href="isComplete?id=${todo.id}&thisPage=${thisPage}" type="button" class="btn btn-success btn-xs">已完成</a>
								<span>( <fmt:formatDate value="${todo.createTime}" type="date" dateStyle="long" />开始  )</span>
							</li>
						</c:when>
						<c:otherwise>
							<li class="todos_NoComplete"><span>${todo.content}</span>&nbsp;&nbsp;&nbsp;
								<a href="noComplete?id=${todo.id}&thisPage=${thisPage}" type="button" class="btn btn-warning btn-xs">未完成</a>
								<span>( <fmt:formatDate value="${todo.createTime}" type="date" dateStyle="long" />开始  )</span>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	<!--内容部分结束-->

	<!--分页部分开始-->
	<nav class="fenye">
		<ul id="pagination" class="pagination">
			<li id="Previous"><a id="a_Previous" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
			<li id="first"><a href="./todo?thisPage=1">1</a></li>
			<li id="Next"><a id="a_Next" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
		<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
			页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
		</small>
	</nav>
	<!--分页部分结束-->
	
	<script type="text/javascript">
	if($('#todo_message').text() != "") {
		alert($('#todo_message').text());
	}
	</script>
	<script type="text/javascript" src="./../../js/page.js"></script>
	
	</c:otherwise>
</c:choose>
	
</body>
</html>
