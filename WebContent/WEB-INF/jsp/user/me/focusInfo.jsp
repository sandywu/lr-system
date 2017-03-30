<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>
			<span>${title}</span>
		</h4>
		<hr />
		<!--内容部分开始-->
		<c:forEach items="${focuses}" var="focus">
		<div style="width:60px;height:100px;float:left;">
			<c:choose>
				<c:when test="${focus.whoFocuNickname==sessionScope.user.nickName}">
				<a href="myself" title="积分：${focus.whoFocuIntegral}">
				</c:when>
				<c:otherwise>
				<a href="friendsIndex?whoCreate=${focus.whoFocuNickname}" title="积分：${focus.whoFocuIntegral}">
				</c:otherwise>
			</c:choose>
				<c:choose>
					<c:when test="${focus.whoFocuPhoto==null}">
						<img src="../../photo/system.jpg" width="50px" height="50px" style="margin:0px;" />
					</c:when>
					<c:otherwise>
						<img src="../../photo/${focus.whoFocuPhoto}" width="50px" height="50px" style="margin:0px;" />
					</c:otherwise>
				</c:choose>
				<p>${focus.whoFocuNickname}</p>
			</a>
		</div>
		</c:forEach>
		<!--内容部分结束-->
	</div>
	<div class="col-md-1"></div>
</div>


<%@ include file="../../template/user-footer.jsp"%>