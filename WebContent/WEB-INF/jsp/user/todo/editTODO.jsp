<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h3>我的TODO</h3>
		<hr />
		<!--内容部分开始-->
		<div class="row edit_cont">
			<div id="editTodo_message" class="alert alert-success" role="alert">${message}</div>
			<h4>新增条目</h4>
			<form action="addTodo" method="get">
				<span class="row"> <input name="content" type="text"
					id="todoContent" class="form-control ueditTODO_inp"
					autofocus="autofocus" placeholder="TODO内容" required/>
					<input name="days" type="number"  min="1" max="100" style="width:130px;"
					class="form-control ueditTODO_ud" placeholder="预计完成天数" required/>
					<input name="priority" type="number" value="50" min="0" max="100"
					class="form-control ueditTODO_ud" /> <span>(优先级为0-100的数字 )</span>
					
					<br /> <small class="todoLength">还可以输入：<span
						id="todoLength">20</span>个字
				</small>
				</span>
				<button type="submit" class="btn btn-primary row ueditTODO_add">新增</button>
			</form>
			<hr />
			<h4>
				已有条目<small>(<span>${count}</span>)
				</small>
			</h4>
			
			<iframe src="./../todo/editTodos?thisPage=1" frameborder="0" width="100%" height="470px;">
			</iframe>
		</div>
		<!--内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>

<script type="text/javascript" src="./../../js/editTODO.js"></script>
<%@ include file="../../template/user-footer.jsp"%>