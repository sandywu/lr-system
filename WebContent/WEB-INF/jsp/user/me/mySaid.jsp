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
<body style="border: 0px; padding: 0px; height: 1200px;width: 600px;">
<c:choose>
	<c:when test="${fn:length(saids)==0}">
	<br/>你还没有写任何说说，快去首页说几句吧...
	</c:when>
	<c:otherwise>
	<div id="del_message">${message}</div>
	<!--内容部分开始-->
	<!--右侧内容部分开始-->
	<c:forEach items="${saids}" var="said">
		<div class="row" style="margin-top: 25px; padding-left: 20px;">
			<!-- 头像部分start -->
			<div style="float:left;">
			<c:choose>
					<c:when test="${sessionScope.user.image == null}">
						<img src="../../photo/system.jpg" width="50px" height="50px"
						 title="积分：${sessionScope.user.integral}" alt="用户头像"/>
					</c:when>
					<c:otherwise>
					 <img src="../../photo/${sessionScope.user.image}" width="50px" 
							height="50px" style="float: left; margin-right: 10px;"
							 title="积分：${sessionScope.user.integral}"  alt="用户头像" />
					</c:otherwise>
			</c:choose>
			</div>
			<!-- 头像部分end -->
			<!-- 内容部分start -->
			<div style="margin-left:60px;">
				<label>${sessionScope.user.nickName} 说：</label>
				<pre>${said.content}</pre>
			</div>
			<div style="margin-left: 60px;">
				<small>	<!-- 创建日期 -->
					<fmt:formatDate value="${said.createTime}" type="date" dateStyle="long" />
				</small>&nbsp;
				<a name="${sessionScope.user.id}=${said.id}" onclick="mySaidpraise(this)" title="说的很好，赞一个">
					<label> 
						<span class="glyphicon glyphicon-thumbs-up"></span> 
						<span>${said.praiseNumber}</span>赞
					</label>
				</a>&nbsp;
				<div class="btn-group btn-group-xs" role="group">
					<!-- <button type="button" class="btn btn-default">Edit</button> -->
					<a href="delSaid?id=${said.id}&thisPage=${thisPage}" type="button" 
					class="btn btn-danger" onclick="return del()">Del</a>
				</div>
			</div>
			<!-- 内容部分end -->
		</div>
	</c:forEach>
	<!--右侧内容部分结束-->
	<!--内容部分结束-->

	<!--分页部分开始-->
	<nav class="fenye">
		<ul id="pagination" class="pagination">
			<li id="Previous"><a id="a_Previous" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
			<li id="first"><a href="./mySaid?thisPage=1">1</a></li>
			<li id="Next"><a id="a_Next" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
		<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
			页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
		</small>
	</nav>
	<!--分页部分结束-->

	<script src="./../../js/ajax.js" type="text/javascript"></script>
	<script src="./../../js/mySaid.js" type="text/javascript"></script>
	<script type="text/javascript" src="./../../js/page.js"></script>
	</c:otherwise>
</c:choose>
</body>
</html>
