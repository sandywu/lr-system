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
					<span>${friends.nickName}</span>&nbsp;&nbsp;
					<c:choose>
						<c:when test="${friends.signature != null}">
							<small>( ${friends.signature} )</small>
						</c:when>
						<c:otherwise>
							<small>( 这家伙很懒，什么也没写... )</small>
						</c:otherwise>
					</c:choose>
				</h4>
				<hr />

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
						<a href="friendsIndex?whoCreate=${friends.nickName}">全部日记 
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
						<iframe src="friendsLog?thisPage=1&whoCreate=${friends.nickName}" frameborder="0" width="100%"
							height="1990px;"> </iframe>
					</div>
					<div role="tabpanel" class="tab-pane" id="said">
						<iframe src="friendsSaid?thisPage=1&whoCreate=${friends.nickName}" frameborder="0" width="100%"
							height="1330px;"> </iframe>
					</div>
				</div>

			</div>
			<!--右侧主体部分结束-->
			<!--左侧内容部分开始-->
			<div class="col-md-4">
				<!--左侧个人头像开始-->
				<div class="row">
					<div style="width: 300px; padding: 20px; background-color: #F1F1F1;">
						<c:choose>
							<c:when test="${friends.image == null}">
								<a class="thumbnail photo"
									title="积分：${friends.integral}"> <img
									src="../../photo/system.jpg" width="150px" height="150px"
									alt="用户头像" />
								</a>
							</c:when>
							<c:otherwise>
								<a class="thumbnail photo"
									title="积分：${friends.integral}"> <img
									src="../../photo/${friends.image}" width="150px"
									height="150px" alt="用户头像" /></a>
							</c:otherwise>
						</c:choose>
						<p style="margin-top: 10px;">
							<span>${friends.nickName}</span><small>&nbsp;于&nbsp;
								<fmt:formatDate value="${friends.createTime}"
									type="date" dateStyle="long" />&nbsp;加入
							</small>
						</p>
						<div class="btn-group btn-group-xs" role="group">
							<c:choose>
								<c:when test="${focusInfo eq true}">
									<!-- 已关注 -->
									<a id="noFocus" name="${friends.id}" type="button" class="btn btn-danger">取消关注</a>
								</c:when>
								<c:otherwise>
									<!-- 未关注 -->
									<a id="noFocus" name="${friends.id}" type="button" class="btn btn-info">关注此人</a>
								</c:otherwise>
							</c:choose>
							 <a href="friendsTodos?thisPage=1&whoCreate=${friends.nickName}" type="button" class="btn btn-default">TODOS</a>
							 <a type="button" class="btn btn-default" data-toggle="modal" data-target="#qrCode">ta的二维码</a>
						</div>
						<c:choose>
							<c:when test="${friends.describ != null}">
								<p style="margin-top: 10px;">${friends.describ}</p>
							</c:when>
							<c:otherwise>
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
								<h4 class="modal-title" id="myModalLabel">ta的二维码</h4>
							</div>
							<div class="modal-body">
								<img src="../../photo/${friends.qrCode}" width="110px" height="110px"
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
						<span>${friends.nickName}</span>的日记本（<span>${fn:length(blogs)}</span>）
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
						<span>${friends.nickName}</span>关注的人（<span>${myFocusNumber}</span>）
					</h4>
					<div class="myself_focus">
					<c:choose>
						<c:when test="${fn:length(myFocuses)==0}">
							ta还没有关注任何人...
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
						<p><a href="friendsFocusInfo?id=${friends.id}">更多...</a></p>
						</c:otherwise>
					</c:choose>
					</div>
				</div>

				<!--左侧已关注用户结束-->
				<!--左侧粉色用户开始-->
				<div class="row">
					<h4>
						关注<span>${friends.nickName}</span>的人（<span>${focusMeNumber}</span>）
					</h4>
					<div class="myself_focus">
					<c:choose>
						<c:when test="${fn:length(focusMes)==0}">
							还没有人关注ta...
						</c:when>
						<c:otherwise>
						<c:forEach items="${focusMes}" var="focusMe">
						<a href="friendsIndex?whoCreate=${focusMe.whoFocuNickname}"
						 title="${focusMe.whoFocuNickname}_积分：${focusMe.whoFocuIntegral}"> 
							<c:choose>
								<c:when test="${focusMe.whoFocuPhoto==null}">
									<img src="../../photo/system.jpg" width="50px" height="50px" />
								</c:when>
								<c:otherwise>
									<img src="../../photo/${focusMe.whoFocuPhoto}" width="50px" height="50px" />
								</c:otherwise>
							</c:choose>
						</a> 
					</c:forEach>
					<p><a href="focusFriendsInfo?id=${friends.id}">更多...</a></p>
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




<script src="./../../js/ajax.js" type="text/javascript"></script>
<script src="./../../js/focus.js" type="text/javascript"></script>
<%@ include file="../../template/user-footer.jsp"%>