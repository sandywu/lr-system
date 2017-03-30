/**
 * 关注该好友
 */
var noFocus = document.getElementById('noFocus');
noFocus.onclick = function checkEmail() {
	ajax({
		method : 'get',
		url : 'focusFriends',
		data : {
			'friendsId' : noFocus.name
		},
		success : function(text) {
			if(text == 0) {	//关注失败
				alert("发生未知错误，关注失败！");
			}
			if(text == 1) {	//关注成功
				noFocus.removeAttribute("class");	//移除原先样式
				noFocus.setAttribute("class", "btn btn-danger");	//移除原先样式
				noFocus.innerHTML = "取消关注";		//设置文本内容
			}
			if(text == 2) {	//取消关注成功
				noFocus.removeAttribute("class");	//移除原先样式
				noFocus.setAttribute("class", "btn btn-info");		//移除原先样式
				noFocus.innerHTML = "关注此人";		//设置文本内容
			}
			if(text == 3) {	//取消关注失败
				alert("发生未知错误，取消关注失败！");
			}
		},
		async : true
	});
}
