<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../template/user-header.jsp"%>


<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-10">
		<h3>账号设置</h3>
		<hr />
		<!--内容部分开始-->
		<div class="row acc_content">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<div id="acc_message" class="alert alert-success" role="alert">${message}</div>
				<div class="row">
					<div class="col-md-1 acc_phone">
						<label>头像</label>
					</div>
					<div class="col-md-11">
						<c:choose>
							<c:when test="${sessionScope.user.image == null}">
								<div class="row">
									<div class="col-md-3">
										<a class="thumbnail photo"> <img
											src="../../photo/system.jpg" width="150px" height="150px"
											alt="用户头像" /><br />
										</a>
									</div>
									<div class="col-md-9">
										<img class="mini_img" src="../../photo/system.jpg"
											width="50px" height="50px" alt="用户头像" />
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-md-3">
										<a class="thumbnail photo"> <img
											src="../../photo/${sessionScope.user.image}" width="150px"
											height="150px" alt="用户头像" /><br />
										</a>
									</div>
									<div class="col-md-9">
										<img class="mini_img"
											src="../../photo/${sessionScope.user.image}" width="50px"
											height="50px" alt="用户头像" />
									</div>
								</div>

							</c:otherwise>
						</c:choose>
						<br /> <a data-toggle="modal" data-target="#photo">修改头像</a>
					</div>
				</div>
				<!-- 修改头像的Modal start -->
				<div class="modal fade" id="photo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog acc_model" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">上传头像</h4>
							</div>
							<form action="uploadImg" method="POST"
								enctype="multipart/form-data">
								<div class="modal-body uindex_ftxt">
									<c:choose>
										<c:when test="${sessionScope.user.image == null}">
											<a class="thumbnail photo"> <img
												src="../../photo/system.jpg" width="150px" height="150px"
												alt="用户头像" /><br />
											</a>
										</c:when>
										<c:otherwise>
											<a class="thumbnail photo"> <img
												src="../../photo/${sessionScope.user.image}" width="150px"
												height="150px" alt="用户头像" /><br />
											</a>
										</c:otherwise>
									</c:choose>
									<br /> <input name="image" type="file" accept="image/jpeg" />
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">修改头像</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Modal end -->

				<form action="updateMe" method="POST" commandName="user">
					<div class="row">
						<div id="acc_nickName" class="col-md-1">
							<label for="acc_nickName_inp">昵称</label>
						</div>
						<div class="col-md-11 acc_box">
							<input name="nickName" type="text" id="acc_nickName_inp"
								class="form-control acc_inp" placeholder="你的昵称..."
								autofocus="autofocus" required="required"
								value="${sessionScope.user.nickName}" /> <small
								id="acc_nickName_error" class="account_nickName_error"></small>
						</div>
					</div>
					<div class="row">
						<div id="acc_signature" class="col-md-1">
							<label for="signature">签名</label>
						</div>
						<div class="col-md-11 acc_box">
							<c:choose>
								<c:when test="${sessionScope.user.signature == null}">
									<input name="signature" id="signature" type="text"
										class="form-control acc_inp" placeholder="秀出你的个性来..." />
								</c:when>
								<c:otherwise>
									<input name="signature" id="signature" type="text"
										class="form-control acc_inp" placeholder="个性签名..."
										value="${sessionScope.user.signature}" />
								</c:otherwise>
							</c:choose>

						</div>
					</div>
					<div class="row">
						<div id="acc_describ" class="col-md-1">
							<label for="describ">简介</label>
						</div>
						<div class="col-md-11 acc_box">
							<c:choose>
								<c:when test="${sessionScope.user.describ == null}">
									<textarea name="describ" id="describ" rows="3" cols="20"
										class="form-control uaccount_txt"
										placeholder="别保持神秘感了，快写点简介吧..."></textarea>
								</c:when>
								<c:otherwise>
									<textarea name="describ" id="describ" rows="3" cols="20"
										class="form-control uaccount_txt" placeholder="个人介绍...">${sessionScope.user.describ}</textarea>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-11">
							<br /> <a data-toggle="modal" data-target="#password">修改密码</a>
						</div>
					</div>
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-11 acc_sub">
							<button type="submit" id="acc_sub" class="btn btn-primary">确认提交！</button>
						</div>
					</div>
				</form>
				<!-- 修改密码的Modal start -->
				<div class="modal fade" id="password" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog acc_model" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">修改密码</h4>
							</div>
							<form action="updatePassword" method="POST"
								enctype="multipart/form-data">
								<div class="modal-body uindex_ftxt">
									<div class="row">
										<div class="col-md-4 register_title">
											<label for="oldPwd">旧密码</label>
										</div>
										<div class="col-md-8 register_inp">
											<input name="oldPwd" type="password" id="oldPwd"
												class="row form-control" placeholder="旧密码"
												required="required" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 register_title">
											<label for="newPwd">新密码</label>
										</div>
										<div class="col-md-8 register_inp">
											<input name="newPwd" type="password" id="newPwd"
												class="row form-control" placeholder="新密码"
												required="required" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 register_title">
											<label for="newPwd2">确认密码</label>
										</div>
										<div class="col-md-8 register_inp">
											<input type="password" id="newPwd2" class="row form-control"
												placeholder="新密码确认" required="required" />
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary">提交修改</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- Modal end -->
			</div>
		</div>

		<!--内容部分结束-->

	</div>
	<div class="col-md-1"></div>
</div>

<script src="./../../js/ajax.js" type="text/javascript"></script>
<script src="./../../js/account.js" type="text/javascript"></script>

<%@ include file="../../template/user-footer.jsp"%>