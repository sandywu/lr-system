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
<body style="border: 0px;padding: 0px;height: 450px;">
<c:choose>
	<c:when test="${fn:length(todos)==0}">
	<br/>你还没有写任何计划，快去添加吧...<br/><br/>
	</c:when>
	<c:otherwise>
	<div class="row" style="width: 700px;">
	<div id="editTodo_message">${message}</div>
	<ul>
		<c:forEach items="${todos}" var="todo" varStatus="status">
			<c:choose>
				<c:when test="${todo.isComplete eq true}">
					<li class="editTodo_li">
					<span class="editTodo_IsComplete">${todo.content}</span>&nbsp;&nbsp;&nbsp;
						<div class="btn-group" role="group">
							<form action="updatePriority" method="get">
							<input name="priority" type="number" value="${todo.priority}" min="0" max="100"
								class="form-control editTodo_priority" />
							<input name="thisPage" type="hidden" value="${thisPage}" />
							<input name="id" type="hidden" value="${todo.id}" />
							<div class="btn-group btn-group-xs ueditTODO_btn" role="group">
								<button type="submit" class="btn btn-default">修改</button> 
								<a href="deleteTodo?id=${todo.id}&thisPage=${thisPage}" type="button"
									class="btn btn-danger" onclick="return del()">删除</a>
							</div>
							</form>
						</div>
						<span>( <fmt:formatDate value="${todo.createTime}" type="date" dateStyle="long" />开始 <span class="todo_isComplete">已完成！</span> )</span>
					</li>
				</c:when>
				<c:otherwise>
					<li class="editTodo_li">
						<span class="editTodo_NoComplete">${todo.content}</span>&nbsp;&nbsp;&nbsp;
						<div class="btn-group" role="group">
							<form action="updatePriority" method="get">
							<input name="priority" type="number" value="${todo.priority}" min="0" max="100"
								class="form-control editTodo_priority" />
							<input name="thisPage" type="hidden" value="${thisPage}" />
							<input name="id" type="hidden" value="${todo.id}" />
							<div class="btn-group btn-group-xs ueditTODO_btn" role="group">
								<button type="submit" class="btn btn-default">修改</button> 
								<a href="deleteTodo?id=${todo.id}&thisPage=${thisPage}" type="button"
									class="btn btn-danger" onclick="return del()">删除</a>
							</div>
							</form>
						</div>
						<span>( <fmt:formatDate value="${todo.createTime}" type="date" dateStyle="long" />开始 <span class="todo_noComplete">未完成！</span> )</span>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>
<!--分页部分开始-->
<nav class="fenye">
	<ul id="pagination" class="pagination">
		<li id="Previous"><a id="a_Previous" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
		<li id="first"><a href="./editTodos?thisPage=1">1</a></li>
		<li id="Next"><a id="a_Next" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
	</ul>
	<small class="page">
	当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
	页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
	</small>
</nav>

<!--分页部分结束-->

<script type="text/javascript">
	if($('#editTodo_message').text() != "") {
		alert($('#editTodo_message').text());
	}
	function del() {
		if(!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
			return false;
		}
	}
</script>
<script type="text/javascript" src="./../../js/page.js"></script>
	</c:otherwise>
</c:choose>
</body>
</html>