<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>
			<span>${sessionScope.user.nickName}</span>关注的日志
		</h4>
		<hr />
		<!--内容部分开始-->
		<div class="row" style="margin-top: 25px;">
			<div class="col-md-1">
				<a href="#" title="用户名"><img src="../../img/qq.png" width="50px"
					height="50px" /></a>
			</div>
			<div class="col-md-11">
				<div>
					<h5>
						<a class="user">XXX的日志</a> <a><small>[日志类型]</small></a> <small>[天气：xxx]</small>
						<small>[心情：xxx]</small> <small>[地点：xxx]</small>
					</h5>
				</div>
				<div class="content">
					<p>开始做毕设了，还没找到工作，要加油了xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
				</div>
				<div>
					<small>2015-11-20 上午 9时44分</small> <small>x回复</small>
				</div>
			</div>
		</div>


		<!--内容部分结束-->
		<!--分页部分开始-->
		<nav class="row fenye">
			<ul class="pagination">
				<li><a aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a>1</a></li>
				<li><a>2</a></li>
				<li><a>3</a></li>
				<li><a>4</a></li>
				<li><a>5</a></li>
				<li><a aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<!--分页部分结束-->
	</div>
	<div class="col-md-1"></div>
</div>


<%@ include file="../../template/user-footer.jsp"%>