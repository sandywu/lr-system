
/**
 * 点击用户头像，进入用户首页
 * @param e
 */
function a(e) {
	if (window != top) {
		top.location.href = e.name;
	}
}


/**
 * 点赞
 * @param e
 */
function friendsSaidpraise(e) {
	ajax({
		method : 'get',
		url : 'praiseSaid',
		data : {
			'whoPraiseId' : (e.name.split("="))[0],
			'praiseSaidId' : (e.name.split("="))[1]
		},
		success : function(text) {
			if(text == 0) {	//点赞失败
				alert("发生未知错误，点赞失败！");
			}
			if(text == 1) {	//点赞成功
				var praiseNumber = parseInt(e.getElementsByTagName("span")[1].innerHTML);
				e.getElementsByTagName("span")[1].innerHTML = praiseNumber + 1;
			}
			if(text == 2) {	//重复点赞
				var praiseNumber = parseInt(e.getElementsByTagName("span")[1].innerHTML);
				e.getElementsByTagName("span")[1].innerHTML = praiseNumber - 1;
			}
		},
		async : true
	});
}