<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>
			<span>${sessionScope.user.nickName}</span>的TODOS&nbsp;&nbsp;
			<c:choose>
				<c:when test="${sessionScope.user.signature != null}">
					<small>(${sessionScope.user.signature})</small>
				</c:when>
				<c:otherwise>
					<small>(这家伙很懒，什么也没写...)</small>
				</c:otherwise>
			</c:choose>
		</h4>
		<a href="myself" type="button" class="btn btn-primary btn-xs"><span>${sessionScope.user.nickName}</span>的主页</a>
		<a href="editTODO" type="button" class="btn btn-primary btn-xs">编辑Todos</a>
		<hr />
		<!--内容部分开始-->
		<iframe src="./../todo/todo?thisPage=1" frameborder="0" width="100%" height="430px;">
		</iframe>
		<!--内容部分结束-->
	</div>
	<div class="col-md-1"></div>
</div>


<%@ include file="../../template/user-footer.jsp"%>