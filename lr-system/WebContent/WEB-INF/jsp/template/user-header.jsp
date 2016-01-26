<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>左右 | 首页 记录生活，记录细节，留住感动...</title>
<link rel="shortcut icon" href="../../img/favicon.ico" />
<link rel="stylesheet" href="../../css/bootstrap.min.css" />
<link rel="stylesheet" href="../../css/lr.min.css" />
<link rel="stylesheet" href="../../css/wangEditor.css">
<script src="../../js/jquery.min.js"></script>
</head>
<body>
	<!--导航条开始-->
	<nav class="navbar navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" id="log" href="index" title="返回首页">左右</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index" title="我关注的日记"><span
							class="glyphicon glyphicon-home"></span> 首页</a></li>
					<li><a href="find" title="所有日记"><span class="glyphicon glyphicon-eye-open"></span>
							发现</a></li>
					<li><a href="myself" title="${sessionScope.user.nickName}的个性主页"><span class="glyphicon glyphicon-user"></span>
							我的主页</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" role="button">
							<span class="glyphicon glyphicon-list"></span> 更多 <span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="TODOS"><span class="glyphicon glyphicon-list-alt"></span> 我的TODOS</a></li>
							<li role="separator" class="divider"></li>
							<li>
								<a href="focusLog?thisPage=1"><span class="glyphicon glyphicon-heart"></span> 关注的日记</a></li>
						</ul>
					</li>
					<form:form class="navbar-form navbar-left" role="search">
						<div class="form-group input-group input-group-sm">
							<input type="text" id="search" class="form-control" placeholder="Search..."
								required="required" />
						</div>
						<!-- 
						<button type="submit" class="btn btn-default"
							style="border-radius: 16px;"><span
						class="glyphicon glyphicon-search"></span> 查找</button>
						 -->
					</form:form>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="addlog" title="添加日记"><span
							class="glyphicon glyphicon-pencil"></span> 写日记</a></li>
					<li><a href="account" title="${sessionScope.user.nickName}的个人资料"><span
							class="glyphicon glyphicon-cog"></span> 账号设置</a></li>
					<li><a href="quit" title="注销账号"><span
							class="glyphicon glyphicon-log-out"></span> 登出</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--导航条结束-->