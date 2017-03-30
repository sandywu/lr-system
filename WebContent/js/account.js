	/**
	 * 登录邮箱校验
	 */
	var acc_nickName = document.getElementById('acc_nickName_inp');
	acc_nickName.onchange = function checkEmail() {
		ajax({
			method : 'get',
			url : 'checkNickName',
			data : {
				'nickName' : acc_nickName.value
			},
			success : function(text) {
				var acc_nickName_error = document.getElementById('acc_nickName_error');
				if (text == 1) { //昵称被占用
					if(acc_nickName_error.innerHTML == ""){
						var errorInfo = document.createTextNode("该昵称已被占用");//创建文本节点
						acc_nickName_error.appendChild(errorInfo);
						acc_nickName_error.style.display = "block"; //显示错误提示信息
						document.getElementById('acc_sub').disabled = "disabled"; //提交按钮不可用
						document.getElementById('acc_nickName_inp').focus(); //昵称输入框获取焦点
					}
				}
				if (text == 0) { //昵称可用
					acc_nickName_error.innerHTML = ""; //将错误提示信息移除
					document.getElementById('acc_sub').disabled = ""; //注册按钮变为可编辑状态
					acc_nickName_error.style.display = "none"; //错误提示div隐藏
				}
			},
			async : true
		});
	}
	
	var newPwd = document.getElementById('newPwd');
	var newPwd2 = document.getElementById('newPwd2');
	newPwd2.onblur = function checkPwd() {
		if(newPwd.value != newPwd2.value) {
			alert("新密码与确认密码不一致，请重新填写新密码！");
			document.getElementById('newPwd').focus();
		}
	}

	
	/**
	 * 显示后台返回的提示信息
	 */
	var messge = document.getElementById('acc_message').innerHTML;
	if(messge == "") {
		document.getElementById('acc_message').style.display = "none";
	}
	