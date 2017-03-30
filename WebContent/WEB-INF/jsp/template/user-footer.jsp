<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- 说说的Modal start -->
<div class="modal fade" id="aboutMe" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog said_modal" role="document">
			<div class="modal-content" style="width:270px;height:320px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">扫码关注</h4>
				</div>
				<div class="modal-body uindex_ftxt">
					<img src="../../img/aboutMe.jpg" width="250px"
						height="250px" title="开发者公众号" />
				</div>
			</div>
	</div>
</div>
<!-- Modal end -->

<hr />
<!--页脚 start-->
<div class="row footer">
	Copyright &copy; 2016 <a data-toggle="modal" data-target="#aboutMe">SHANKS</a>
</div>
<!--页脚 end-->

<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/public.js"></script>
</body>
</html>