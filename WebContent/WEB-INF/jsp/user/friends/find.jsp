<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>全部动态</h4>
		<hr />
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active">
				<a href="find">全部日志</a>
			</li>
			<li role="presentation">
				<a href="#said" aria-controls="said" role="tab" data-toggle="tab">全部说说</a>
			</li>
		</ul>

		<!--内容部分开始-->
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="log">
				<iframe src="./../friends/allLog?thisPage=1" frameborder="0" width="100%"
							height="3150px;"> </iframe>
			</div>
			<div role="tabpanel" class="tab-pane" id="said">
				<iframe src="./../friends/allSaid?thisPage=1" frameborder="0" width="100%"
							height="1330px;"> </iframe>
			</div>
		</div>
		<!--内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>


<%@ include file="../../template/user-footer.jsp"%>