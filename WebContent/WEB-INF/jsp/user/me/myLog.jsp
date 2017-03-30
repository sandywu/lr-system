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
<body style="border: 0px; padding: 0px; width: 600px;">
<c:choose>
	<c:when test="${fn:length(logs)==0}">
	<br/>你还没有写任何日志哦，快去添加日志吧...<br/><br/>
	</c:when>
	<c:otherwise>
	<div id="del_message">${message}</div>
	<!--内容部分开始-->
	<!--右侧内容部分开始-->
	<c:forEach items="${logs}" var="log">
		<div class="row" style="margin-top: 25px; padding-left: 20px;">
			<!-- 头像部分start -->
			<div style="float:left;">
			<c:choose>
				<c:when test="${sessionScope.user.image == null}">
					<img src="../../photo/system.jpg" width="50px" height="50px"
						title="积分：${sessionScope.user.integral}" alt="用户头像" />
				</c:when>
				<c:otherwise>
					<img src="../../photo/${sessionScope.user.image}" width="50px"
						height="50px" style="float: left; margin-right: 10px;"
						title="积分：${sessionScope.user.integral}" alt="用户头像" />
				</c:otherwise>
			</c:choose>
			</div>
			<!-- 头像部分end -->
			<!-- 标签部分start -->
			<div style="margin-left:60px;">
				<h5>
					<a name="../me/logInfo?id=${log.id}&whoCreate=${log.whoCreate}"
						onclick="return a(this)" class="user">标题：${log.title}</a> <a
						name="blogInfo?thisPage=1&blogName=${log.blogName}&whoCreate=${log.whoCreate}"
						onclick="return a(this)"><small>[ ${log.blogName} ]</small></a>
					<c:choose>
						<c:when test="${log.weather != ''}">
							<small>[ 天气：${log.weather} ]</small>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${log.mood != ''}">
							<small>[ 心情：${log.mood}]</small>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${log.place != ''}">
							<small>[ 地点：${log.place} ]</small>
						</c:when>
					</c:choose>
				</h5>
			</div>
			<!-- 标签部分end -->
			<!-- 内容部分start -->
			<div class="content">
				<c:choose>
					<c:when test="${fn:length(log.content) > 120}">
						<pre style="margin-left: 60px;">${fn:substring(log.content, 0, 120)}<a
								name="../me/logInfo?id=${log.id}&whoCreate=${log.whoCreate}"
								onclick="return a(this)" style="float: right;">更多...</a></pre>
					</c:when>
					<c:otherwise>
						<pre style="margin-left: 60px;">${log.content}</pre>
					</c:otherwise>
				</c:choose>
			</div>
			<!-- 内容部分end -->
			<div style="margin-left: 60px;">
				<small> <!-- 创建日期 --> <fmt:formatDate
						value="${log.createTime}" type="date" dateStyle="long" />
				</small>
				<!-- 他人是否可见 -->
				<c:choose>
					<c:when test="${log.visible eq true}">
						<!-- 公开日志 -->
						<small>公开日志</small>
					</c:when>
					<c:otherwise>
						<small>私密日志</small>
					</c:otherwise>
				</c:choose>
				<!-- 他人是否可回复 -->
				<c:choose>
					<c:when test="${log.reply eq true}">
						<!-- 可回复 -->
						<small>可回复</small>
					</c:when>
					<c:otherwise>
						<small>不可回复</small>
					</c:otherwise>
				</c:choose>
				<!-- 回复数量 -->
				<small>${log.replyNumber}回复</small>&nbsp; <a title="进入详情页点赞"> <label>
						<span class="glyphicon glyphicon-thumbs-up"></span> <span>${log.praiseNumber}</span>赞
				</label>
				</a>&nbsp;
				<div class="btn-group btn-group-xs" role="group">
					<!-- <button type="button" class="btn btn-default">Edit</button> -->
					<a href="delLog?id=${log.id}&thisPage=${thisPage}" type="button"
						class="btn btn-danger" onclick="return del()">Del</a>
				</div>
			</div>
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
			<li id="first"><a href="./myLog?thisPage=1">1</a></li>
			<li id="Next"><a id="a_Next" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span></a></li>
		</ul>
		<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
			页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
		</small>
	</nav>
	<!--分页部分结束-->

	<script type="text/javascript">
		if ($('#del_message').text() != "") {
			alert($('#del_message').text());
		}
		function a(e) {
			if (window != top) {
				top.location.href = e.name;
			}
		}
		function del() {
			if (!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
				return false;
			}
		}
	</script>
	<script type="text/javascript" src="./../../js/page.js"></script>
	</c:otherwise>
</c:choose>
</body>
</html>
