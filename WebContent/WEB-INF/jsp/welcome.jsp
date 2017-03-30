<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="template/public-header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!--导航条开始-->
<nav class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" id="log" href="index" title="刷新页面">左右</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index" title="刷新页面"><span
						class="glyphicon glyphicon-home"></span> 首页</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="register" title="注册新账号"><span
						class="glyphicon glyphicon-registration-mark"></span> 注册</a></li>
			</ul>
		</div>
	</div>
</nav>
<!--导航条结束-->

<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<div class="row">
			<!--右侧主体部分开始-->
			<div class="col-md-8">
				<div class="wel_msg">
					<h4>记录生活，记录细节，留住感动...</h4>
					<h5>
						此时已有<span>${userNumber}</span>位朋友创建了
						<span>${blogNumber}</span>本日志, 写下了
						<span>${logNumber}</span>件小事，
						<span>${saidNumber}</span>个说说，
						<span>${todoNumber}</span>条心愿
					</h5>
				</div>
				<hr />
				<h4>最新日志</h4>
				<hr />
				<!--右侧内容部分开始-->
				<c:choose>
					<c:when test="${fn:length(logs)==0}">
					当前还没有任何人写日志...
					</c:when>
					<c:otherwise>
					<c:forEach items="${logs}" var="log">
					<div class="row" style="margin-top: 25px;">
						<div class="col-md-1">
							<!-- 头像部分start -->
							<a onclick="message()" class="user" title="登录后可查看更多信息">
							<c:choose>
								<c:when test="${log.userPhoto == null}">
									<img src="./photo/system.jpg" width="50px" height="50px"
										title="${log.whoCreate}" alt="用户头像" />
								</c:when>
								<c:otherwise>
									<img src="./photo/${log.userPhoto}" width="50px"
										height="50px" style="float: left; margin-right: 10px;"
										title="${log.whoCreate}" alt="用户头像" />
								</c:otherwise>
							</c:choose>
							</a>
							<!-- 头像部分end -->
						</div>
						<div class="col-md-11">
							<!-- 标签部分start -->
							<div>
								<h5>
									<a title="登录后可查看更多信息" class="user">标题：${log.title}</a> 
									<a title="登录后可查看更多信息">
										<small>[ ${log.blogName} ]</small>
									</a>
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
										<pre>${fn:substring(log.content, 0, 120)}<a title="登录后可查看更多信息" style="float: right;">更多...</a></pre>
									</c:when>
									<c:otherwise>
										<pre>${log.content}</pre>
									</c:otherwise>
								</c:choose>
							</div>
							<!-- 内容部分end -->
							<div>
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
										<!-- 回复数量 -->
										<small>${log.replyNumber}回复</small>
									</c:when>
									<c:otherwise>
										<small>不可回复</small>
									</c:otherwise>
								</c:choose>&nbsp;
								<a title="登录后就可以点赞啦...">
									<label> 
										<span class="glyphicon glyphicon-thumbs-up"></span> 
										<span>${log.praiseNumber}</span>赞
									</label>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
				<!--右侧内容部分结束-->
				<!--分页部分开始-->
				<nav class="fenye" style="float: right;">
					<ul class="pagination">
						<li><a onclick="message()" title="登录后可查看更多信息">更多日志...</a></li>
					</ul>
				</nav>
				<!--分页部分结束-->
					</c:otherwise>
				</c:choose>
			</div>
			<!--右侧主体部分结束-->

			<!--左侧内容部分开始-->
			<div class="col-md-4">
				<!--左侧登录开始-->
				<div class="wel_login">
					<h4>登录左右</h4>
					<hr />
					<form:form action="login" method="POST" commandName="user">
						<div id="log_error" class="reg_message">${message}</div>
						<img id="log_img" width="140px" height="90px" src="./img/beforelogin.gif" />
						<div class="row wel_box">
							<div class="col-md-3 wel_lab">
								<label for="email" title="登录邮箱"> <span
									class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</label>
							</div>
							<div class="col-md-9 wel_finp">
								<form:input path="email" type="email" id="log_email" class="row form-control wel_inp"
									placeholder="Email" required="required" />
							</div>
						</div>
						<div class="row wel_box">
							<div class="col-md-3 wel_lab">
								<label for="password" title="登录密码"> <span
									class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</label>
							</div>
							<div class="col-md-9 wel_finp">
								<form:input path="password" type="password" class="row form-control wel_inp"
									id="password" placeholder="Password" required="required" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-10 wel_rem">
								<a href="findPwd"  title="找回密码">忘记密码</a>&nbsp;&nbsp; 
								<a href="register"  title="注册新账号">注册</a>&nbsp;
								<small id="log_message" class="log_message"></small>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-10 wel_sub">
								<button type="submit" id="log_sub" class="btn btn-primary wel_log">
									<span class="glyphicon glyphicon-log-in"></span> 登录
								</button>
							</div>
						</div>

					</form:form>
				</div>
				<!--左侧登录结束-->
				<hr />
				<div class="wel_hot">
					<p>
						<span class="glyphicon glyphicon-time"></span> <span id="time">距离明天还有：</span>
					</p>
				</div>
				<hr />
				<h4>最新动态</h4>
				<hr />
				<!--左侧最新动态开始-->
				<div>
					<div class="wel_recent">
					<!-- 最新动态 start-->
					<c:choose>
						<c:when test="${fn:length(news)==0}">
						当前无最新动态...<br/><br/>
						</c:when>
						<c:otherwise>
						<c:forEach items="${news}" var="new1">
						<p>
							<c:choose>
								<c:when test="${new1.flag=='todo'}">
								<a title="登录后可查看更多信息">${new1.whoCreate}</a> TODO:
									<c:choose>
										<c:when test="${fn:length(new1.content)>10}">
											${fn:substring(new1.content,0,10)} ...
										</c:when>
										<c:otherwise>${new1.content}</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${new1.flag=='said'}">
									<a title="登录后可查看更多信息">${new1.whoCreate}</a> 说:
									<c:choose>
										<c:when test="${fn:length(new1.content)>10}">
										${fn:substring(new1.content,0,10)} ...
										</c:when>
										<c:otherwise>${new1.content}</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<a title="登录后可查看更多信息">${new1.whoCreate}</a>
									 发表了一篇日志...
								</c:otherwise>
							</c:choose>	
						</p>
					</c:forEach>
					<!-- 最新动态 end-->
					<a onclick="message()" title="登录后可查看更多信息">更多动态...</a>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
				<!--左侧最新动态结束-->

				<h4>活跃用户</h4>
				<hr />
				<!--左侧活跃用户开始-->
				<div class="activ">
				<c:choose>
					<c:when test="${fn:length(actives)==0}">
					当前无任何用户...
					</c:when>
					<c:otherwise>
						<c:forEach items="${actives}" var="active">
							<a title="${active.nickName}_积分：${active.integral}"> 
							<c:choose>
								<c:when test="${active.image==null}">
									<img src="./photo/system.jpg" width="50px" height="50px" />
								</c:when>
								<c:otherwise>
									<img src="./photo/${active.image}" width="50px" height="50px" />
								</c:otherwise>
							</c:choose>
							</a> 
						</c:forEach>
						<p><a onclick="message()" title="登录后可查看更多信息">更多好友...</a></p>
					</c:otherwise>
				</c:choose>
				</div>
				<!--左侧活跃用户结束-->

			</div>
		</div>
		<!--左侧内容部分结束-->


	</div>
	<div class="col-md-1"></div>
</div>

<script src="./js/ajax.js" type="text/javascript"></script>
<script src="./js/login.js" type="text/javascript"></script>
<script type="text/javascript">
	//alert("本网站在火狐浏览器下开发测试，强烈建议使用火狐浏览器进行访问！！！谢谢配合！");
	$(function() {
		var date = new Date();
		var hours = 23 - date.getHours();
		var minutes = 60 - date.getMinutes();
		var txt = "<span>" + hours + '小时' + minutes + '分钟' + " </span>";
		$('#time').after(txt);
		
	});
	function message() {
		alert("登录后可以查看都多信息。先登录吧...");
	}
</script>

<%@ include file="template/footer.html"%>