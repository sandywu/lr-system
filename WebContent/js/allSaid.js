
/**
 * 点击用户头像进入用户首页
 * @param e
 */
function a(e) {
	if (window != top) {
		top.location.href = e.name;
	}
}

/**
 * 删除说说前提示
 * @returns {Boolean}
 */
function del() {
	if(!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
		return false;
	}
}



function allSaidpraise(e) {
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