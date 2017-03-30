<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>
<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>全部系统通知</h4>
		<hr />
		<!--内容部分开始-->
		<c:forEach items="${stmMsgs}" var="stmMsg">
			<div style="margin-left: 60px;">
				<h5>
					<label>标题:${stmMsg.title}</label>
				</h5>
			</div>
			
			<pre style="margin-left: 60px;">${stmMsg.content}</pre>
			<div style="margin-left: 60px;">
				<small>发布日期：
					<fmt:formatDate value="${stmMsg.startTime}" type="date" dateStyle="long" />
				</small>
			</div>
			<hr />
		</c:forEach>
		<!--内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>


<%@ include file="../../template/user-footer.jsp"%>