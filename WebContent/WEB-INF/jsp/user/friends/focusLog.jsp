<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>我关注的日志</h4>
		<hr />
		<c:choose>
			<c:when test="${fn:length(logs)==0}">
			<br/>当前无任何关注的日志信息...<br/><br/>
			</c:when>
			<c:otherwise>
			<c:forEach items="${logs}" var="log">
			<div class="row" style="margin-top: 25px; padding-left: 20px;">
				<!-- 头像部分start -->
				<div style="float:left;">
				<c:choose>
					<c:when test="${log.whoCreate==sessionScope.user.nickName}">
						<a class="user" href="myself">
					</c:when>
					<c:otherwise>
						<a class="user"
							href="friendsIndex?whoCreate=${log.whoCreate}">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${log.userPhoto == null}">
						<img src="../../photo/system.jpg" width="50px" height="50px"
							title="${log.whoCreate}" alt="用户头像" />
					</c:when>
					<c:otherwise>
						<img src="../../photo/${log.userPhoto}" width="50px" height="50px"
							style="float: left; margin-right: 10px;" title="${log.whoCreate}"
							alt="用户头像" />
					</c:otherwise>
				</c:choose>
				</a>
				</div>
				<!-- 头像部分end -->
				<!-- 标签部分start -->
				<div style="margin-left:60px;">
					<h5>
						<label>${log.whoCreate}的日志:</label> <a class="user"
							href="logInfo?id=${log.id}&whoCreate=${log.whoCreate}">
							标题：${log.title}</a> 
							<a href="blogInfo?thisPage=1&blogName=${log.blogName}&whoCreate=${log.whoCreate}">
							<small>[ ${log.blogName} ]</small></a>
						<c:choose>
							<c:when test="${log.weather != ''}">
								<small>[ 天气：${log.weather} ]</small>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${log.mood != ''}">
								<small>[ 心情：${log.mood}]</small>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${log.place != ''}">
								<small>[ 地点：${log.place} ]</small>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${log.whoCreate==sessionScope.user.nickName}">
								<a href="focusDelLog?logId=${log.id}&thisPage=${thisPage}"
									class="logInfo_delReply" onclick="return del()"> <span
									class="glyphicon glyphicon-trash"></span>
								</a>
							</c:when>
						</c:choose>
					</h5>
				</div>
				<!-- 标签部分end -->
				<!-- 内容部分start -->
				<div class="content">
					<c:choose>
						<c:when test="${fn:length(log.content) > 520}">
							<pre style="margin-left: 60px;">${fn:substring(log.content, 0, 520)} 
						<a name="../me/logInfo?id=${log.id}&whoCreate=${log.whoCreate}"
									onclick="return a(this)" style="float: right;">更多...</a></pre>
						</c:when>
						<c:otherwise>
							<pre style="margin-left: 60px;">${log.content}</pre>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- 内容部分end -->
				<div style="margin-left: 60px;">
					<small> <!-- 创建日期 --> <fmt:formatDate
							value="${log.createTime}" type="date" dateStyle="long" />
					</small>
					<!-- 他人是否可见 -->
					<small>公开日志</small>
					<!-- 他人是否可回复 -->
					<c:choose>
						<c:when test="${log.reply eq true}">
							<!-- 可回复 -->
							<small>可回复</small>
							<!-- 回复数量 -->
							<small>${log.replyNumber}回复</small>
						</c:when>
						<c:otherwise>
							<small>不可回复</small>
						</c:otherwise>
					</c:choose>&nbsp;
					<a title="进入详情页点赞">
						<label> 
						<span class="glyphicon glyphicon-thumbs-up"></span> 
						<span>${log.praiseNumber}</span>赞
						</label>
					</a>
				</div>
			</div>
		</c:forEach>
		<!--右侧内容部分结束-->
		<!--内容部分结束-->

		<!--分页部分开始-->
		<nav class="fenye">
			<ul id="pagination" class="pagination">
				<li id="Previous"><a id="a_Previous" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
				</a></li>
				<li id="first"><a href="focusLog?thisPage=1">1</a></li>
				<li id="Next"><a id="a_Next" aria-label="Next"> <span
						aria-hidden="true">&raquo;</span></a></li>
			</ul>
			<small class="page"> 当前第 <span id="thisPage" class="thisPage">${thisPage}</span>
				页，共有 <span id="pageSize" class="pageSize">${pageSize}</span> 页
			</small>
		</nav>
		<!--分页部分结束-->
		<script type="text/javascript">
		function del() {
			if (!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
				return false;
			}
		}
		</script>
		<script type="text/javascript" src="./../../js/page.js"></script>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-1"></div>
</div>

<%@ include file="../../template/user-footer.jsp"%>