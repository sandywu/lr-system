<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="template/public-header.jsp"%>


<!--导航条开始-->
<nav class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" id="log" href="index">左右</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index"><span
						class="glyphicon glyphicon-home"></span> 首页</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index"> <span
						class="glyphicon glyphicon-log-in"></span> 登录
				</a></li>
			</ul>
		</div>
	</div>
</nav>
<!--导航条结束-->
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-5">
		<!--主体部分开始-->
		<div class="row">
			<form action="findPwd" method="POST" onsubmit="return checkPwd()">
				<fieldset>
					<h3>找回密码.</h3>
					<hr />
					<div id="reg_message" class="reg_message">${message}</div>
					<div class="row" id="email_box">
						<div class="col-md-3 register_title">
							<label for="email">登录邮箱</label>
						</div>
						<div class="col-md-9 register_inp">
							<input name="email" type="email" id="email"
								class="row form-control"
								placeholder="登录邮箱...(填写完后焦点移开)" required="required" />
						</div>
					</div>
					<div class="row" id="qeustion_box" style="display:none;">
						<div class="col-md-3 register_title">
							<label for="qeustion">问题</label>
						</div>
						<div class="col-md-9 register_inp">
							<input type="text" id="qeustion" class="row form-control" 
							placeholder="找回密码的问题" disabled="disabled" required="required" />
						</div>
					</div>
					<div class="row" id="answer_box" style="display: none;">
						<div class="col-md-3 register_title">
							<label for="answer">答案</label>
						</div>
						<div class="col-md-9 register_inp">
							<input type="text" id="answer" class="row form-control" 
							placeholder="问题的答案...(回答完将焦点移开)" required="required" />
						</div>
					</div>
					<div class="row" id="password_box" style="display:none;">
						<div class="col-md-3 register_title">
							<label for="password">新密码</label>
						</div>
						<div class="col-md-9 register_inp">
							<input name="password" type="password" id="password"
								class="row form-control" placeholder="新密码"
								required="required" />
						</div>
					</div>
					<div class="row" id="password2_box" style="display:none;">
						<div class="col-md-3 register_title">
							<label for="password2">确认密码</label>
						</div>
						<div class="col-md-9 register_inp">
							<input type="password" id="password2"
								class="row form-control" placeholder="确认密码" required="required" />
						</div>
					</div>
					 <br />
					<div class="row" id="but_box" style="display:none;">
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<button type="submit" id="reg_sub"
								class="btn btn-primary register_btn">
								<span class="glyphicon glyphicon-registration-mark"></span> 提交
							</button>
							&nbsp;&nbsp;&nbsp;<a href="index"> <span
								class="glyphicon glyphicon-log-in"></span> 想起密码了，我去登录啦...
							</a>
						</div>
					</div>
					<br />
				</fieldset>
			</form>
		</div>
		<!--主体部分结束-->

	</div>
	<div class="col-md-4"></div>
</div>

<script src="./js/ajax.js" type="text/javascript"></script>
<script src="./js/findPwd.js" type="text/javascript"></script>

<%@ include file="template/footer.html"%>