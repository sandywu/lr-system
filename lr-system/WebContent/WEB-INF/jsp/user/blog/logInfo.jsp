<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>

<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h4>
			<span>${author.nickName}</span>的日记&nbsp;&nbsp;
			<c:choose>
				<c:when test="${author.signature != null}">
					<small>( ${author.signature} )</small>
				</c:when>
				<c:otherwise>
					<small>( 这家伙很懒，什么也没写... )</small>
				</c:otherwise>
			</c:choose>
		</h4>
		<c:choose>
			<c:when test="${author.nickName eq sessionScope.user.nickName}">
				<!-- 我的主页 -->
				<a href="myself" type="button" class="btn btn-primary btn-xs"> <span>我</span>
					的主页
				</a>
			</c:when>
			<c:otherwise>
				<a href="friendsIndex?whoCreate=${author.nickName}" type="button"
					class="btn btn-primary btn-xs"> <span>${author.nickName}</span>
					的主页
				</a>
			</c:otherwise>
		</c:choose>

		<hr />
		<!--内容部分开始-->
		<div class="row" style="margin-top: 25px; padding-left: 20px;">
			<!-- 头像部分start -->
			<div style="float: left;">
				<c:choose>
					<c:when test="${author.nickName==sessionScope.user.nickName}">
						<a class="user" href="myself">
					</c:when>
					<c:otherwise>
						<a class="user" href="friendsIndex?whoCreate=${author.nickName}">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${author.image == null}">
						<img src="../../photo/system.jpg" width="50px" height="50px"
							title="积分：${author.integral}" alt="用户头像" />
					</c:when>
					<c:otherwise>
						<img src="../../photo/${author.image}" width="50px" height="50px"
							style="float: left; margin-right: 10px;"
							title="积分：${author.integral}" alt="用户头像" />
					</c:otherwise>
				</c:choose>
				</a>
			</div>
			<!-- 头像部分end -->
			<!-- 标签部分start -->
			<div style="margin-left: 60px;">
				<h5>
					<a class="user">标题：${log.title}</a> <a
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
				</h5>
			</div>
			<!-- 标签部分end -->
			<!-- 内容部分start -->
			<div class="content">
				<pre style="margin-left: 60px;">${log.content}</pre>
			</div>
			<!-- 内容部分end -->
			<div style="margin-left: 60px;">
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
						&nbsp;
						<a title="回复在下方"> <label> <span
								class="glyphicon glyphicon-envelope"></span> <span>回复(
									${fn:length(replys)} )</span>
						</label>
						</a>
					</c:when>
					<c:otherwise>
						<small>不可回复</small>
					</c:otherwise>
				</c:choose>
				&nbsp; <a id="praise" name="${sessionScope.user.id},${log.id}">
					<label title="写的很好，赞一个"> <span
						class="glyphicon glyphicon-thumbs-up"></span> 赞( <span
						id="praiseNumber">${praiseNumber}</span> )
				</label>
				</a>&nbsp; <a data-toggle="modal" data-target="#report"> <label
					title="内容非法，举报该日记"> <span
						class="glyphicon glyphicon-thumbs-down"></span> 举报
				</label>
				</a>
			</div>
			<!-- 举报的Modal start -->
			<div class="modal fade" id="report" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog said_modal" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">举报该日记</h4>
						</div>
						<div class="modal-body uindex_ftxt">
							<input id="logId" type="hidden" value="${log.id}"/>
							<textarea id="reportContent" rows="3" cols="20" placeholder="举报原因..."
								class="form-control uindex_txt" required="required"></textarea>
							<small class="uindex_small">还可以输入：<span id="saidLength">30</span>个字
							</small>
						</div>
						<div class="modal-footer">
							<input id="sub" type="button" class="btn btn-primary" value="提交举报" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 举报Modal end -->
		</div>
		<!--内容部分结束-->
		<!-- 回复内容start -->
		<c:forEach items="${replys}" var="reply">
			<div class="row">
				<div class="col-md-1">
					<div class="logInfo_img">
						<!-- 头像部分start -->
						<c:choose>
							<c:when
								test="${reply.replyerNickname==sessionScope.user.nickName}">
								<a class="user" href="myself">
							</c:when>
							<c:otherwise>
								<a class="user"
									href="friendsIndex?whoCreate=${reply.replyerNickname}">
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${reply.replyerPhoto == null}">
								<img src="../../photo/system.jpg" width="50px" height="50px"
									title="积分：${reply.integral}" alt="用户头像" />
							</c:when>
							<c:otherwise>
								<img src="../../photo/${reply.replyerPhoto}" width="50px"
									height="50px" style="float: left; margin-right: 10px;"
									title="积分：${reply.integral}" alt="用户头像" />
							</c:otherwise>
						</c:choose>
						</a>
						<!-- 头像部分end -->
					</div>
				</div>
				<div class="col-md-8 logInfo_replyCont">
					<!-- 内容部分start -->
					<small> <!-- 回复日期 --> <fmt:formatDate
							value="${reply.replyDate}" type="date" dateStyle="long" />
					</small> <label>${reply.replyerNickname}</label>
					<c:choose>
						<c:when test="${reply.replyerSignature != null}">
							<small>( ${reply.replyerSignature} )</small>
						</c:when>
						<c:otherwise>
							<small>( 这家伙很懒，什么也没写... )</small>
						</c:otherwise>
					</c:choose>
					<!-- 是否存在二次回复start -->
					<c:choose>
						<c:when test="${reply.replyer2Nickname != null}">
							<label>&nbsp;回复&nbsp;${reply.replyer2Nickname}</label>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					<!-- 是否存在二次回复end -->
					<c:choose>
						<c:when
							test="${reply.replyerNickname==sessionScope.user.nickName}">
							<a
								href="delReply?replyId=${reply.replyId}&logId=${log.id}&whoCreate=${log.whoCreate}"
								class="logInfo_delReply" onclick="return del()"> <span
								class="glyphicon glyphicon-trash"></span>
							</a>
						</c:when>
					</c:choose>
					<a class="reply" data-toggle="modal"
						data-target="#${reply.replyId}">回复</a>
					<div>
						<!-- 回复信息 -->
						<pre>${reply.replyContent}</pre>
					</div>
					<!-- 回复的Modal start -->
					<div class="modal fade" id="${reply.replyId}" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog said_modal" role="document">
							<form action="addReply2" method="post">
								<input name="logId" type="hidden" value="${log.id}" /> <input
									name="whoCreate" type="hidden" value="${log.whoCreate}" /> <input
									name="replyer2Nickname" type="hidden"
									value="${reply.replyerNickname}" />
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">回复${reply.replyerNickname}</h4>
									</div>
									<div class="modal-body uindex_ftxt">
										<textarea name="content" rows="3" cols="20"
											class="form-control uindex_txt" required="required"></textarea>
									</div>
									<div class="modal-footer">
										<input type="submit" class="btn btn-primary" value="确认回复" />
										<button type="button" class="btn btn-default"
											data-dismiss="modal">关闭</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- Modal end -->
					<!-- 内容部分end -->
				</div>
				<div class="col-md-3"></div>
			</div>
		</c:forEach>
		<!-- 回复内容end -->
		<!-- 回复部分start -->
		<c:choose>
			<c:when test="${log.reply eq true}">
				<div class="logInfo_reply">
					<form action="addReply" method="POST">
						<input name="logId" type="hidden" value="${log.id}" /> <input
							name="whoCreate" type="hidden" value="${log.whoCreate}" />
						<textarea name="content" rows="3" cols="20"
							class="form-control logInfo_txt" required="required"></textarea>
						<input type="submit" class="btn btn-primary reply_but" value="回复" />
					</form>
				</div>
			</c:when>
		</c:choose>

		<!-- 回复部分end -->
	</div>
	<div class="col-md-1"></div>
</div>

<script src="./../../js/ajax.js" type="text/javascript"></script>
<script src="./../../js/logInfo.js" type="text/javascript"></script>
<%@ include file="../../template/user-footer.jsp"%>