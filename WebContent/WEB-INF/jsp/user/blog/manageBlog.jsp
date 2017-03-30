<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h3>我的日志本</h3>
		<hr />
		<!--内容部分开始-->
		<div id="editTodo_message" class="alert alert-success" role="alert">${message}</div>
		<div class="row edit_cont">
			<div id="editTodo_message" class="alert alert-success" role="alert">${message}</div>
			<h4>新增日志本</h4>
			<form action="addBlog" method="get">
				<span class="row"> <input name="name" type="text"
					id="todoContent" class="form-control ueditTODO_inp"
					autofocus="autofocus" placeholder="日志本名..." /> <small
					class="todoLength">还可以输入：<span id="todoLength">10</span>个字
				</small>
				</span> <br />
				<button type="submit" class="btn btn-primary row ueditTODO_add">新增</button>
			</form>
			<h4>
			已有日志本<small>( <span>${fn:length(blogs)}</span> ) 
			->( <span>默认日志本不可删除，删除的日志本下所有日志将会自动转移到默认日志本下</span> )
				</small>
			</h4>
			<br />
			<c:choose>
				<c:when test="${fn:length(blogs) gt 1}">
					<form action="delBlog" method="get">
						<input name="whoCreate" value="${blogs[0].whoCreate}" type="hidden" /> 
						<select name="name" class="form-control" id="addlog_sel"
							data-style="btn-info">
							<c:forEach items="${blogs}" var="blog">
								<c:choose>
									<c:when test="${blog.name eq '默认日志'}"></c:when>
									<c:otherwise>
										<option>${blog.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <br />
						<button type="submit" class="btn btn-primary row ueditTODO_add" onclick="return del()">
						删除</button>
					</form>
				</c:when>
				<c:otherwise>
					你还没有创建任何自定义日志本哦...
				</c:otherwise>
			</c:choose>

		</div>
		<!--内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>

<script type="text/javascript">
		function del() {
			if (!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
				return false;
			}
		}
</script>
<script type="text/javascript" src="./../../js/editTODO.js"></script>
<%@ include file="../../template/user-footer.jsp"%>