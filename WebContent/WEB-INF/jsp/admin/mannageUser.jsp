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
				<td>用户昵称</td>
				<td>邮箱</td>
				<td>状态</td>
				<td>积分</td>
				<td>账号创建日期</td>
				<td>是否是管理员</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="user">
		<c:choose>
			<c:when test="${user.state==1}"><tr class="success"></c:when>
			<c:otherwise><tr class="danger"></c:otherwise>
		</c:choose>
			
				<td>${user.nickName}</td>
				<td>${user.email}</td>
				<td>
				<c:choose>
					<c:when test="${user.state==1}">账号可用</c:when>
					<c:otherwise>已被禁用</c:otherwise>
				</c:choose>
				</td>
				<td>${user.integral}分</td>
				<td><fmt:formatDate value="${user.createTime}" type="date" dateStyle="long" /></td>
				
				<c:choose>
					<c:when test="${user.isAdmin eq false}">
						<td>普通用户</td>
						<td>
						<c:choose>
							<c:when test="${user.state==1}">
								<a style="color:#337AB7;" href="disable?id=${user.id}&toState=0&thisPage=${thisPage}">禁用该账号</a>
							</c:when>
							<c:otherwise>
								<a style="color:#337AB7;" href="available?id=${user.id}&toState=1&thisPage=${thisPage}">解除禁用</a>
							</c:otherwise>
						</c:choose>
							
						</td>
					</c:when>
					<c:otherwise>
						<td>管理员</td>
						<td>不可操作</td>
					</c:otherwise>
				</c:choose>
				
				
			</tr>
		</c:forEach>
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
					href="mannageUser?thisPage=1">1</a></li>
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
