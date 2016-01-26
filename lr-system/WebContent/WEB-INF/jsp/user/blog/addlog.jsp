<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h3>写日记</h3>
		<hr />
		<!--内容部分开始-->
		<div id="addLog_message" class="alert alert-success" role="alert">${message}</div>
		<div class="row addlog_cont">
			<form:form action="addLog" method="post">
				<div class="control-group">
					<div class="input-group">
						<select name="blogName" class="form-control" id="addlog_sel"
							data-style="btn-info">
							<c:forEach items="${blogs}" var="blog">
								<option>${blog.name}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp; 
						<a href="manageBlog">管理我的日记本</a>
					</div>
				</div>
				<div class="control-group">
					<div class="input-group">
						<span class="input-group-btn"><label for="addlog_mood">心情：</label>&nbsp;&nbsp;&nbsp;
						</span>
						<input name="mood" type="text" class="form-control"
							id="addlog_mood" autofocus="autofocus" placeholder="喜,怒,哀,乐..." />
					</div>
				</div>
				<div class="control-group">
					<div class="input-group">
						<span class="input-group-btn"> <label for="addlog_weather">天气：</label>&nbsp;&nbsp;&nbsp;
						</span>
						<input name="weather" type="text" class="form-control"
							id="addlog_weather" placeholder="阴,晴,风,雨..." />
					</div>
				</div>
				<div class="control-group">
					<div class="input-group">
						<span class="input-group-btn"> <label for="addlog_place">地点：</label>&nbsp;&nbsp;&nbsp;
						</span>
						<input name="place" type="text" class="form-control"
							id="addlog_place" placeholder="写日记的地点..." />
					</div>
				</div>
				<div class="control-group">
					<div class="input-group">
						<span class="input-group-btn"> <label for="addlog_title">标题：</label>&nbsp;&nbsp;&nbsp;
						</span>
						<input name="title" type="text" class="form-control"
							id="addlog_title" placeholder="日记的标题..." required="required" />
					</div>
				</div>
				<div class="control-group">
					<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
					<small>由于使用火狐浏览器开发，强烈推荐使用火狐浏览器发布日记~</small>
				</div>
				<div class="control-group">
					<textarea name="content" id="textarea1" style="height:400px;" required="required"></textarea>
				</div>
				<div class="control-group">
					<label><input name="visible1" type="checkbox" />&nbsp;仅自己可见</label>&nbsp;&nbsp;&nbsp;
					<label><input name="reply1" type="checkbox" />&nbsp;不允许被回复</label>
				</div>
				<div class="control-group">
					<button type="submit" class="btn btn-primary">发表</button>
				</div>
			</form:form>
		</div>
		<!--内容部分结束-->
		
	</div>
	<div class="col-md-1"></div>
</div>



<!-- <script type="text/javascript" src='../../js/jquery-1.10.2.min.js'></script> -->
<script type="text/javascript" src='../../js/wangEditor.js'></script>
<script type="text/javascript" src='../../js/addLog.js'></script>

<%@ include file="../../template/user-footer.jsp"%>