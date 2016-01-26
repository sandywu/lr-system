<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<div class="row">
			<!--右侧主体部分开始-->
			<div class="col-md-8">
				<!--  -->
				<h4>
					<span>${sessionScope.user.nickName}</span>&nbsp;&nbsp;
					<c:choose>
						<c:when test="${sessionScope.user.signature != null}">
							<small>( ${sessionScope.user.signature} )</small>
						</c:when>
						<c:otherwise>
							<small>( 快去完善资料秀出你的个性来... )</small>
						</c:otherwise>
					</c:choose>
				</h4>
				<hr />

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
						<a href="myself">全部日记 
							<span class="badge">${logCount}</span>
						</a>
					</li>
					<li role="presentation">
						<a href="#said" aria-controls="said" role="tab" data-toggle="tab">全部说说 
							<span class="badge">${saidCount}</span>
						</a>
					</li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="log">
						<iframe src="myLog?thisPage=1" frameborder="0" width="100%"
							height="1930px;"> </iframe>
					</div>
					<div role="tabpanel" class="tab-pane" id="said">
						<iframe src="mySaid?thisPage=1" frameborder="0" width="100%"
							height="1330px;"> </iframe>
					</div>
				</div>

			</div>
			<!--右侧主体部分结束-->
			<!--左侧内容部分开始-->
			<div class="col-md-4">
				<!--左侧个人头像开始-->
				<div class="row">
					<div
						style="width: 300px; padding: 20px; background-color: #F1F1F1;">
						<c:choose>
							<c:when test="${sessionScope.user.image == null}">
								<a class="thumbnail photo"
									title="积分：${sessionScope.user.integral}"> <img
									src="../../photo/system.jpg" width="150px" height="150px"
									alt="用户头像" />
								</a>
							</c:when>
							<c:otherwise>
								<a class="thumbnail photo"
									title="积分：${sessionScope.user.integral}"> <img
									src="../../photo/${sessionScope.user.image}" width="150px"
									height="150px" alt="用户头像" /></a>
							</c:otherwise>
						</c:choose>
						<p style="margin-top: 10px;">
							<span>${sessionScope.user.nickName}</span><small>&nbsp;于&nbsp;
								<fmt:formatDate value="${sessionScope.user.createTime}"
									type="date" dateStyle="long" />&nbsp;加入
							</small>
						</p>
						<div class="btn-group btn-group-xs" role="group">
							<a href="TODOS" type="button" class="btn btn-default">TODOS</a> 
							<a type="button" class="btn btn-default" data-toggle="modal" data-target="#qrCode">我的二维码</a>
						</div>
						<c:choose>
							<c:when test="${sessionScope.user.describ != null}">
								<p style="margin-top: 10px;">${sessionScope.user.describ}</p>
							</c:when>
							<c:otherwise>
								<p style="margin-top: 10px;">别保持神秘感了，快去写点简介吧...</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!-- 二维码的Modal start -->
				<div class="modal fade" id="qrCode" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog said_modal" role="document">
						<div class="modal-content" style="width:150px;height:195px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">我的二维码</h4>
							</div>
							<div class="modal-body">
								<img src="../../photo/${sessionScope.user.qrCode}" width="110px" height="110px"
								alt="用户二维码" /><br />
							</div>
						</div>
					</div>
				</div>
				<!-- Modal end -->
				<!--左侧个人头像结束-->
				<!--左侧个人日记开始-->
				<div class="row">
					<h4>
						<span>${sessionScope.user.nickName}</span>的日记本（<span>${fn:length(blogs)}</span>）
					</h4>
					<div>
						<div class="recent">
							<c:forEach items="${blogs}" var="blog">
								<p>
									<a href="blogInfo?thisPage=1&blogName=${blog.name}&whoCreate=${blog.whoCreate}"><span>${blog.name}</span>（<span>${blog.size}</span>）</a>
								</p>
							</c:forEach>

						</div>
					</div>
				</div>

				<!--左侧个人日记结束-->
				<!--左侧已关注用户开始-->
				<div class="row">
					<h4>
						我关注的人（<span>${myFocusNumber}</span>）
					</h4>
					<div class="myself_focus">
					<c:choose>
						<c:when test="${fn:length(myFocuses)==0}">
							你还没有关注任何人，快去发现里看看志趣相投的好友吧...
						</c:when>
						<c:otherwise>
						<c:forEach items="${myFocuses}" var="myFocus">
						<a href="friendsIndex?whoCreate=${myFocus.whoFocuNickname}"
						 title="${myFocus.whoFocuNickname}_积分：${myFocus.whoFocuIntegral}"> 
							<c:choose>
								<c:when test="${myFocus.whoFocuPhoto==null}">
									<img src="../../photo/system.jpg" width="50px" height="50px" />
								</c:when>
								<c:otherwise>
									<img src="../../photo/${myFocus.whoFocuPhoto}" width="50px" height="50px" />
								</c:otherwise>
							</c:choose>
						</a> 
					</c:forEach>
						<p><a href="myFocusInfo?id=${sessionScope.user.id}">更多...</a></p>
						</c:otherwise>
					</c:choose>
					</div>
				</div>

				<!--左侧已关注用户结束-->
				<!--左侧粉色用户开始-->
				<div class="row">
					<h4>
						关注我的人（<span>${focusMeNumber}</span>）
					</h4>
					<div class="myself_focus">
					<c:choose>
						<c:when test="${fn:length(focusMes)==0}">
							可能是你不够活跃，还没有人关注你...
						</c:when>
						<c:otherwise>
						<c:forEach items="${focusMes}" var="focusMe">
						<a href="friendsIndex?whoCreate=${focusMe.whoFocuNickname}"
						 title="${focusMe.whoFocuNickname}_积分：${focusMe.whoFocuIntegral}"> 
							<c:choose>
								<c:when test="${focusMe.whoFocuPhoto == null}">
									<img src="../../photo/system.jpg" width="50px" height="50px" />
								</c:when>
								<c:otherwise>
									<img src="../../photo/${focusMe.whoFocuPhoto}" width="50px" height="50px" />
								</c:otherwise>
							</c:choose>
						</a> 
					</c:forEach>
						<p><a href="focusMeInfo?id=${sessionScope.user.id}">更多...</a></p>
						</c:otherwise>
					</c:choose>
					
					</div>
				</div>

				<!--左侧粉色用户结束-->
			</div>
		</div>
		<!--左侧内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>




<%@ include file="../../template/user-footer.jsp"%>