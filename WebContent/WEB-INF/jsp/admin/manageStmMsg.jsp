<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../template/admin-header.jsp" %>
<body>
	<div class="header">
		<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
		  发布消息
		</button>
	</div>
	<div class="table-responsive section">
	<table class="table table-bordered table-hover" style="text-align:center;">
		<thead>
			<tr style="background-color:#8F8F8F;color:white;">
				<td>标题</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>当前状态</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
<c:choose>
	<c:when test="${fn:length(stmMsgs)==0}">
		<tr><td colspan="5"><h1>暂无任何系统消息...</h1></td></tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${stmMsgs}" var="stmMsg">
			<c:choose>
				<c:when test="${stmMsg.state==1}"><tr class="success"></c:when>
				<c:otherwise><tr class="danger"></c:otherwise>
			</c:choose>
			
				<td>${stmMsg.title}</td>
				<td><fmt:formatDate value="${stmMsg.startTime}" type="date" dateStyle="long" /></td>
				<td>
				<c:choose>
					<c:when test="${empty stmMsg.endTime}">展示中...</c:when>
					<c:otherwise>
					<fmt:formatDate value="${stmMsg.endTime}" type="date" dateStyle="long" />
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
					<c:when test="${stmMsg.state==1}">发布状态...</c:when>
					<c:otherwise>消息已过期...</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
					<c:when test="${stmMsg.state==1}">
						<a href="updateState?id=${stmMsg.id}&toState=0&thisPage=${thisPage}"style="color:#337AB7;">更新为过期</a>
					</c:when>
					<c:otherwise>
						<a href="updateState?id=${stmMsg.id}&toState=1&thisPage=${thisPage}"style="color:#337AB7;">更新为发布</a>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>


		</tbody>
	</table>
	<!-- 发布消息的Modal start -->
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">发布系统消息</h4>
      </div>
      <form action="addStmMsg" method="POST">
      <div class="modal-body">
        <div class="form-group">
		    <label for="title">标题</label>
		    <input name="title" type="text" maxlength="20" class="form-control" placeholder="标题" required/>
		</div>
		<div class="form-group">
		    <label for="content">详细信息</label>
		    <textarea name="content" rows="5" maxlength="20000" class="form-control" placeholder="详细信息..." required></textarea>
		</div>
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary">发布</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
      </form>
    </div>
  </div>
</div>		
	<!-- Modal end -->
	</div>
	
	
	
<c:choose>
	<c:when test="${pageSize>0}">
	<!--分页部分开始-->
	<div class="footer">
		<nav class="fenye">
			<ul id="pagination" class="pagination">
				<li id="Previous"><a id="a_Previous" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
				<li id="first"><a
					href="manageStmMsg?thisPage=1">1</a></li>
				<li id="Next"><a id="a_Next" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span></a></li>
			</ul>
			<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
				页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
			</small>
		</nav>
	</div>
		<!--分页部分结束-->
	</c:when>
</c:choose>
		
	<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/page.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>
