<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>
			<span>${whoCreate}</span>的TODOS&nbsp;&nbsp;
		</h4>
		<a href="friendsIndex?whoCreate=${whoCreate}" type="button"
			class="btn btn-primary btn-xs"><span>${whoCreate} </span>的主页</a>
		<hr />
<c:choose>
	<c:when test="${fn:length(todos)==0}">这家伙很懒，还没有写任何的TODO...</c:when>
	<c:otherwise>
		<!--内容部分开始-->
		<div class="row todos_Todos" style="width: 700px;">
			<ul>
				<c:forEach items="${todos}" var="todo" varStatus="status">
					<c:choose>
						<c:when test="${todo.isComplete eq true}">
							<li class="todos_Complete"><span>${todo.content}</span>&nbsp;&nbsp;&nbsp;
								<a type="button" class="btn btn-success btn-xs">已完成</a> <span>(
									<fmt:formatDate value="${todo.createTime}" type="date"
										dateStyle="long" />开始 )
							</span></li>
						</c:when>
						<c:otherwise>
							<li class="todos_NoComplete"><span>${todo.content}</span>&nbsp;&nbsp;&nbsp;
								<a type="button" class="btn btn-warning btn-xs">未完成</a> <span>(
									<fmt:formatDate value="${todo.createTime}" type="date"
										dateStyle="long" />开始 )
							</span></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		<!--内容部分结束-->

		<!--分页部分开始-->
		<nav class="fenye">
			<ul id="pagination" class="pagination">
				<li id="Previous"><a id="a_Previous" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
				<li id="first"><a
					href="./todo?thisPage=1&whoCreate=${todos[0].whoCreate}">1</a></li>
				<li id="Next"><a id="a_Next" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span></a></li>
			</ul>
			<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
				页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
			</small>
		</nav>
		
		<script type="text/javascript" src="./../../js/pageForFriendsLog.js"></script>
		<!--分页部分结束-->
	</c:otherwise>
</c:choose>
	</div>
	<div class="col-md-1"></div>
</div>

<%@ include file="../../template/user-footer.jsp"%>