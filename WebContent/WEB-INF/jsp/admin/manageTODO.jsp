<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../template/admin-header.jsp" %>
<body>
	<div class="header">
	
	</div>
	<div class="table-responsive section">
	<table class="table table-bordered table-hover" style="text-align:center;">
		<thead>
			<tr style="background-color:#8F8F8F;color:white;">
				<td>创建人昵称</td>
				<td>创建日期</td>
				<td>TODO详情</td>
				<td>优先级</td>
				<td>是否完成</td>
			</tr>
		</thead>
		<tbody>
<c:choose>
	<c:when test="${fn:length(todos)==0}">
		<tr><td colspan="5"><h1>暂无任何TODO信息...</h1></td></tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${todos}" var="todo">
			<c:choose>
				<c:when test="${todo.isComplete eq true}"><tr class="success"></c:when>
				<c:otherwise><tr class="danger"></c:otherwise>
			</c:choose> 
			
				<td>${todo.whoCreate}</td>
				<td><fmt:formatDate value="${todo.createTime}" type="date" dateStyle="long" /></td>
				<td>${todo.content}</td>
				<c:choose>
					<c:when test="${todo.priority>80}">
						<td>非常高</td>
					</c:when>
					<c:when test="${60<todo.priority && todo.priority <80}">
						<td>较高</td>
					</c:when>
					<c:when test="${40<todo.priority && todo.priority <60}">
						<td>一般</td>
					</c:when>
					<c:otherwise><td>较低</td></c:otherwise>
				</c:choose> 
				<td>
				<c:choose>
					<c:when test="${todo.isComplete eq true}">已完成</c:when>
					<c:otherwise>未完成</c:otherwise>
				</c:choose> 
				</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
		</tbody>
	</table>
	</div>
<c:choose>
	<c:when test="${pageSize>0}">
		<!--分页部分开始-->
	<div class="footer">
		<nav class="fenye">
			<ul id="pagination" class="pagination">
				<li id="Previous"><a id="a_Previous" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
				<li id="first"><a
					href="manageTODO?thisPage=1">1</a></li>
				<li id="Next"><a id="a_Next" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span></a></li>
			</ul>
			<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
				页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
			</small>
		</nav>
	</div>
		<!--分页部分结束-->
	</c:when>
</c:choose>	
	<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/page.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>
