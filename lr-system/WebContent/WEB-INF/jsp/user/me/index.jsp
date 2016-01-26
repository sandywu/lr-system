<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<div class="row">
			<!--右侧主体部分开始-->
			<div class="col-md-8">
				<h4>
					<span>${sessionScope.user.nickName}</span>的动态
				</h4>
				<hr />
				<!--右侧内容部分开始-->
				<c:choose>
					<c:when test="${fn:length(logs)==0}">
					当前无任何好友动态...
					</c:when>
					<c:otherwise>
					<c:forEach items="${logs}" var="log">
					<div class="row" style="margin-top: 25px;">
						<div class="col-md-1">
							<!-- 头像部分start -->
							<c:choose>
								<c:when test="${log.whoCreate==sessionScope.user.nickName}">
									<a class="user" href="myself">
								</c:when>
								<c:otherwise>
									<a class="user" href="friendsIndex?whoCreate=${log.whoCreate}">
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${log.userPhoto == null}">
									<img src="../../photo/system.jpg" width="50px" height="50px"
										title="${log.whoCreate}" alt="用户头像" />
								</c:when>
								<c:otherwise>
									<img src="../../photo/${log.userPhoto}" width="50px"
										height="50px" style="float: left; margin-right: 10px;"
										title="${log.whoCreate}" alt="用户头像" />
								</c:otherwise>
							</c:choose>
							</a>
							<!-- 头像部分end -->
						</div>
						<div class="col-md-11">
							<!-- 标签部分start -->
							<div>
								<h5>
									<a href="logInfo?id=${log.id}&whoCreate=${log.whoCreate}"
										class="user">标题：${log.title}</a> <a
										href="blogInfo?thisPage=1&blogName=${log.blogName}&whoCreate=${log.whoCreate}">
										<small>[ ${log.blogName} ]</small>
									</a>
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
											<a href="indexDelLog?logId=${log.id}"
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
									<c:when test="${fn:length(log.content) > 120}">
										<pre>${fn:substring(log.content, 0, 120)} 
										<a href="logInfo?id=${log.id}&whoCreate=${log.whoCreate}"
												style="float: right;">更多...</a></pre>
									</c:when>
									<c:otherwise>
										<pre>${log.content}</pre>
									</c:otherwise>
								</c:choose>
							</div>
							<!-- 内容部分end -->
							<div>
								<small> <!-- 创建日期 --> <fmt:formatDate
										value="${log.createTime}" type="date" dateStyle="long" />
								</small>
								<!-- 他人是否可见 -->
								<c:choose>
									<c:when test="${log.visible eq true}">
										<!-- 公开日记 -->
										<small>公开日记</small>
									</c:when>
									<c:otherwise>
										<small>私密日记</small>
									</c:otherwise>
								</c:choose>
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
					</div>
				</c:forEach>
				<!--右侧内容部分结束-->
				<!--分页部分开始-->
				<nav class="fenye" style="float: right;">
					<ul class="pagination">
						<li><a href="focusLog?thisPage=1">更多日记...</a></li>
					</ul>
				</nav>
				<!--分页部分结束-->
					</c:otherwise>
				</c:choose>
			</div>
			<!--右侧主体部分结束-->
			<!--左侧内容部分开始-->
			<div class="col-md-4">
				<h4>
					欢迎回来,<span>${sessionScope.user.nickName}</span>!
				</h4>
				<hr />
				<!--左侧菜单栏开始-->
				<div class="btn-group" role="group">
					<a href="addlog" type="button" class="btn btn-default" title="写日记">写日记</a>
					<button type="button" class="btn btn-default" title="说说"
						data-toggle="modal" data-target="#shuoshuo">说说</button>
					<a href="manageBlog" type="button" class="btn btn-default"
						title="管理日记本">管理日记本</a> <a href="TODOS" type="button"
						class="btn btn-default" title="管理TODO">管理TODO</a>
				</div>
				<div id="uindex_message">${message}</div>

				<!-- 说说的Modal start -->
				<div class="modal fade" id="shuoshuo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog said_modal" role="document">
						<form action="addSaid" method="get" onsubmit="return checkSaid();">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">写说说</h4>
								</div>
								<div class="modal-body uindex_ftxt">
									<textarea name="content" id="saidContent" rows="3" cols="20"
										class="form-control uindex_txt" required="required"></textarea>
									<small class="uindex_small">还可以输入：<span id="saidLength">30</span>个字
									</small>
								</div>
								<div class="modal-footer">
									<input type="submit" class="btn btn-primary" value="发表" />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- Modal end -->
				<!--左侧菜单栏结束-->
				<hr />
				<div class="uindex_hot">
					<p>
						<span class="glyphicon glyphicon-time"></span> <span id="time">距离明天还有：</span>
					</p>
				</div>
				<hr />
				<h4>最新动态</h4>
				<hr />
				<!--左侧最新动态开始-->
				<div>
					<div class="recent">
					<!-- 最新动态 start-->
					<c:choose>
						<c:when test="${fn:length(news)==0}">
						当前无任何最新动态...<br/><br/>
						</c:when>
						<c:otherwise>
						<c:forEach items="${news}" var="new1">
						<p>
							<c:choose>
								<c:when test="${new1.flag=='todo'}">
									<c:choose>
										<c:when test="${new1.whoCreate==sessionScope.user.nickName}">
										<a href="myself">${new1.whoCreate}</a>
										</c:when>
										<c:otherwise>
										<a href="friendsIndex?whoCreate=${new1.whoCreate}">${new1.whoCreate}</a>
										</c:otherwise>
									</c:choose>
									 TODO:
									<c:choose>
										<c:when test="${fn:length(new1.content)>10}">
											${fn:substring(new1.content,0,10)} ...
										</c:when>
										<c:otherwise>${new1.content}</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${new1.flag=='said'}">
									<c:choose>
										<c:when test="${new1.whoCreate==sessionScope.user.nickName}">
										<a href="myself">${new1.whoCreate}</a>
										</c:when>
										<c:otherwise>
										<a href="friendsIndex?whoCreate=${new1.whoCreate}">${new1.whoCreate}</a>
										</c:otherwise>
									</c:choose>
									 说:
									<c:choose>
										<c:when test="${fn:length(new1.content)>10}">
										${fn:substring(new1.content,0,10)} ...
										</c:when>
										<c:otherwise>${new1.content}</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${new1.whoCreate==sessionScope.user.nickName}">
										<a href="myself">${new1.whoCreate}</a>
										</c:when>
										<c:otherwise>
										<a href="friendsIndex?whoCreate=${new1.whoCreate}">${new1.whoCreate}</a>
										</c:otherwise>
									</c:choose>
									 发表了一篇日记...
								</c:otherwise>
							</c:choose>	
						</p>
					</c:forEach>
					<!-- 最新动态 end-->
					<a href="find">更多动态...</a>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
				<!--左侧最新动态结束-->
				<h4>活跃用户</h4>
				<hr />
				<!--左侧活跃用户开始-->
				<div class="activ">
				<c:forEach items="${actives}" var="active">
					<c:choose>
						<c:when test="${active.nickName==sessionScope.user.nickName}">
							<a href="myself"
						 	title="${active.nickName}_积分：${active.integral}"> 
						</c:when>
						<c:otherwise>
							<a href="friendsIndex?whoCreate=${active.nickName}"
						 	title="${active.nickName}_积分：${active.integral}"> 
						</c:otherwise>
					</c:choose>
							<c:choose>
								<c:when test="${active.image==null}">
									<img src="../../photo/system.jpg" width="50px" height="50px" />
								</c:when>
								<c:otherwise>
									<img src="../../photo/${active.image}" width="50px" height="50px" />
								</c:otherwise>
							</c:choose>
						</a> 
				</c:forEach>
				<p><a href="active">更多...</a></p>
				</div>
				<!--左侧活跃用户结束-->
			</div>
		</div>
		<!--左侧内容部分结束-->
	</div>
	<div class="col-md-1">
	</div>
</div>

<script type="text/javascript">
	function del() {
		if (!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
			return false;
		}
	}
</script>
<script type="text/javascript" src="./../../js/uindex.js"></script>
<%@ include file="../../template/user-footer.jsp"%>