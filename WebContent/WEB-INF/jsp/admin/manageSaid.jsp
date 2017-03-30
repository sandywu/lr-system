<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../template/admin-header.jsp" %>
<body>
	<div class="header">
	
	</div>
	<div class="table-responsive section">
	<table class="table table-bordered table-hover" style="text-align:center;">
		<thead>
			<tr style="background-color:#8F8F8F;color:white;">
				<td>创建人昵称</td>
				<td>创建日期</td>
				<td>说说内容</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
<c:choose>
	<c:when test="${fn:length(saids)==0}">
		<tr><td colspan="4"><h1>暂无任何说说信息...</h1></td></tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${saids}" var="said">
			<tr>			
				<td>${said.whoCreate}</td>
				<td><fmt:formatDate value="${said.createTime}" type="date" dateStyle="long" /></td>
				<td>${said.content}</td>
				<td><a data-id="${said.id}" style="color:#337AB7;" href="javascript:;" onclick="delSiad(this)">删除</a></td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
		</tbody>
	</table>
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
					href="manageSaid?thisPage=1">1</a></li>
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
	<script type="text/javascript" src="${basePath}/js/page.js"></script>
	<script type="text/javascript">
	function delSiad(ths) {
		if(confirm("您确认要删除该条说说（相关联的所有信息都将被删除）？该操作不可逆！！！")) {
			//location.href = "mdelSaid?id="+ths.data('id')+"&thisPage="+$('#thisPage').html();
			location.href = "mdelSaid?id="+ths.getAttribute('data-id')
					+"&thisPage="+document.getElementById('thisPage').innerHTML;
		}
	}
	</script>
</body>
</html>
