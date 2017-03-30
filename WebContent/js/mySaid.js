
/**
 * 后台返回提示信息
 */
if($('#del_message').text() != "") {
	alert($('#del_message').text());
}

/**
 * 删除前提示
 * @returns {Boolean}
 */
function del() {
	if(!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
		return false;
	}
}

/**
 * 点赞
 * @param e
 */
function mySaidpraise(e) {
	//var whoPraiseId = (e.name.split("="))[0];
	//var praiseSaidId = (e.name.split("="))[1];
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